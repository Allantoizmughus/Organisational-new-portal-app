package Dao;

import model.User;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

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
        String sql="select * from users";
        try(Connection con = sql2o.open()){
            return con.createQuery(sql).executeAndFetch(User.class);
        }
    }

    @Override
    public User findById(int id) {
        return null;
    }

    @Override
    public void update(int id, String name, String position, String role, int departmentId) {

    }

    @Override
    public void clearAll() {

    }
}
