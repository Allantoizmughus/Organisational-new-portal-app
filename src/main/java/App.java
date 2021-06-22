import Dao.Sql2oDepartmentDao;
import Dao.Sql2oNewsDao;
import Dao.Sql2oUserDao;
import com.google.gson.Gson;
import model.User;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static spark.Spark.post;


public class App {

    public static void main(String[] args){
        Sql2oDepartmentDao departmentDao;
        Sql2oNewsDao newsDao;
        Sql2oUserDao userDao;
        Connection conn;
        Gson gson =new Gson();

        String connectionString="";
        Sql2o sql2o=new Sql2o(connectionString,"","");

        departmentDao = new Sql2oDepartmentDao(sql2o);
        newsDao = new Sql2oNewsDao(sql2o);
        userDao = new Sql2oUserDao(sql2o);
        conn = sql2o.open();

        post("/User/new", "application/json", (req,res)->{
            User user =gson.fromJson(req.body(), User.class);

            userDao.add(user);
            res.status(201);
            res.type("application/json");
            return gson.toJson(user);
        });
    }
}
