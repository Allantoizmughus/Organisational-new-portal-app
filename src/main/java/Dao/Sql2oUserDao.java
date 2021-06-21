package Dao;

import model.Department;
import model.News;
import model.User;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.ArrayList;
import java.util.List;

public class Sql2oUserDao implements UserDao {
    private Sql2o sql2o;

    public Sql2oUserDao(Sql2o sql2o){
        this.sql2o=sql2o;
    }

    @Override
    public void add(User user) {
        String sql="INSERT INTO users(id,name,position,role,departmentId) VALUES(:id, :name, :position,:role, :departmentId)";
        try(Connection con = sql2o.open()){
            int id = (int) con.createQuery(sql,true)
                    .bind(user)
                    .executeUpdate()
                    .getKey();
            user.setId(id);
        }
    }

    @Override
    public void addDepartmentUser(User user, Department department) {
        String sql = "INSERT INTO department_users (departmentId, userId) VALUES (:departmentId, :userId)";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("departmentId", department.getId())
                    .addParameter("userId", user.getId())
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public List<User> getAll() {
        String sql="SELECT * FROM users";
        try(Connection con = sql2o.open()){
            return con.createQuery(sql).executeAndFetch(User.class);
        }
    }

    @Override
    public List<User> getAllDepartmentUser(int departmentId) {
        List<User> user = new ArrayList(); //empty list
        String joinQuery = "SELECT userId FROM department_users WHERE departmentId = :departmentId";

        try (Connection con = sql2o.open()) {
            List<Integer> allUserIds = con.createQuery(joinQuery)
                    .addParameter("departmentId", departmentId)
                    .executeAndFetch(Integer.class);
            for (Integer userId : allUserIds){
                String newsQuery = "SELECT * FROM users WHERE id = :userId";
                user.add(
                        con.createQuery(newsQuery)
                                .addParameter("userId", userId)
                                .executeAndFetchFirst(User.class));
            }
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
        return user;

    }

    @Override
    public User findById(int id) {
        String sql ="SELECT * FROM users WHERE id = :id ";
        try(Connection con = sql2o.open()){
            return con.createQuery(sql).addParameter("id",id).executeAndFetchFirst(User.class);
        }
    }

    @Override
    public void update(User user, String name, String position, String role, int departmentId) {
        String sql = "UPDATE users SET  (name,position,role,departmentId) = ( :name,:position,:role,:departmentId) WHERE id= :id ";
        try(Connection con = sql2o.open()){
            con.createQuery(sql)
                    .addParameter("name",name)
                    .addParameter("position",position)
                    .addParameter("role",role)
                    .addParameter("departmentId",departmentId)
                    .addParameter("id",user.getId())
                    .executeUpdate();

            user.setName(name);
            user.setPosition(position);
            user.setRole(role);
            user.setDepartmentId(departmentId);
        }

    }

    @Override
    public void clearAll() {
        String sql = "DELETE FROM users";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql).executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);

        }
    }
}
