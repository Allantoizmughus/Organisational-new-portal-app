package Dao;

import model.User;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

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
    public List<User> getAll() {
        String sql="SELECT * FROM users";
        try(Connection con = sql2o.open()){
            return con.createQuery(sql).executeAndFetch(User.class);
        }
    }

    @Override
    public User findById(int id) {
        String sql ="SELECT * FROM users WHERE id = :id ";
        try(Connection con = sql2o.open()){
            return con.createQuery(sql).addParameter("id",id).executeAndFetchFirst(User.class);
        }
    }

    @Override
    public void update(User user,int id, String name, String position, String role, int departmentId) {
        String sql = "UPDATE users SET  (id,name,position,role,departmentId) = (:id :name,:position,:role,:departmentId) where id= :id ";
        try(Connection con = sql2o.open()){
            con.createQuery(sql)
                    .addParameter("id",id)
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
