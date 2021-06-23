import Dao.Sql2oDepartmentDao;
import Dao.Sql2oNewsDao;
import Dao.Sql2oUserDao;
import com.google.gson.Gson;
import model.Department;
import model.News;
import model.User;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static spark.Spark.*;


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

        post("/user/new", "application/json", (req,res)->{
            User user =gson.fromJson(req.body(), User.class);

            userDao.add(user);
            res.status(201);
            res.type("application/json");
            return gson.toJson(user);
        });

        post("/news/new", "application/json",(req,res)->{
            News news=gson.fromJson(req.body(), News.class);

            newsDao.add(news);
            res.status(201);
            res.type("application/json");
            return gson.toJson(news);

        });
        post("departments/new","application/json", (req,res)->{
            Department department= gson.fromJson(req.body(), Department.class);

            departmentDao.add(department);
            res.status(201);
            res.type("application/json");
            res.redirect("/department");
            return null;
        });

        post("/departments/:departmentId/news/:newsId","application/json",(req,res)->{
            int departmentId = Integer.parseInt(req.params("departmentId"));
            int newsId = Integer.parseInt(req.params("newsId"));
            Department department = departmentDao.findById(departmentId);
            News news = newsDao.findById(newsId);

            if (department != null && news != null) {
                //both exist and can be associated
                newsDao.addDepartmentNews(news, department);
                res.status(201);
                return gson.toJson(String.format("Department '%s' and News '%s' have been associated", news.getContent(), department.getName()));
            } else {
                return null;
            }
        });

        post("/departments/:departmentId/users/:userId","application/json",(req,res)->{
            int departmentId = Integer.parseInt(req.params("departmentId"));
            int userId = Integer.parseInt(req.params("userId"));
            Department department = departmentDao.findById(departmentId);
            User user = userDao.findById(userId);

            if (department != null && user != null) {
                //both exist and can be associated
                userDao.addDepartmentUser(user, department);
                res.status(201);
                return gson.toJson(String.format("Department '%s' and User '%s' have been associated", user.getName(), department.getName()));
            } else {
                return null;
            }
        });

        get("/user",(req,res)->{
            res.type("application/json");
            return gson.toJson(userDao.getAll());
        });

        get("/users/:id",(req,res)->{
            res.type("application/json");
            int userId = Integer.parseInt(req.params("id"));
            res.type("application/json");
            return gson.toJson(userDao.findById(userId));
        });

        get("/departments", (req,res)->{
            res.type("application/json");
            return  gson.toJson(departmentDao.getAll());
        });

        get("/departments/:id",(req,res)->{
            res.type("application/json");
            int departmentId = Integer.parseInt(req.params("id"));
            res.type("application/json");
            return gson.toJson(departmentDao.findById(departmentId));
        });

        get("/departments/:id/users",(req,res)->{
            res.type("application/json");
            int departmentId = Integer.parseInt(req.params("id"));
            res.type("application/json");
            return gson.toJson(departmentDao.findById(departmentId));
        });

        get("/departments/:id/news",(req,res)->{
            res.type("application/json");
            int departmentId = Integer.parseInt(req.params("id"));
            res.type("application/json");
            return gson.toJson(departmentDao.findById(departmentId));
        });
    }
}
