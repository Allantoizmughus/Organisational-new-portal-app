import Dao.Sql2oDepartmentDao;
import Dao.Sql2oNewsDao;
import Dao.Sql2oUserDao;
import com.google.gson.Gson;
import model.Department;
import model.News;
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

        post("/News/new", "application/json",(req,res)->{
            News news=gson.fromJson(req.body(), News.class);

            newsDao.add(news);
            res.status(201);
            res.type("application/json");
            return gson.toJson(news);

        });
        post("Department/new","application/json", (req,res)->{
            Department department= gson.fromJson(req.body(), Department.class);

            departmentDao.add(department);
            res.status(201);
            res.type("application/json");
            res.redirect("/department");
            return null;
        });
    }
}
