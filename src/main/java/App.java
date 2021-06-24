import Dao.Sql2oDepartmentDao;
import Dao.Sql2oNewsDao;
import Dao.Sql2oUserDao;
import com.google.gson.Gson;
import model.Department;
import model.News;
import model.User;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

import static spark.Spark.*;


public class App {

    public static void main(String[] args){
        Sql2oDepartmentDao departmentDao;
        Sql2oNewsDao newsDao;
        Sql2oUserDao userDao;
        Connection conn;
        Gson gson =new Gson();

        String connectionString="jdbc:postgresql://localhost:5432/news_portal";
        Sql2o sql2o=new Sql2o(connectionString,"moringa","Access");

        departmentDao = new Sql2oDepartmentDao(sql2o);
//        {
////            @Override
////            public List<Department> getAll(){
////                return null;
////            }
//        };
        newsDao = new Sql2oNewsDao(sql2o);
        userDao = new Sql2oUserDao(sql2o);
        conn = sql2o.open();

        post("/user/new", "application/json", (req,res)->{
            User user =gson.fromJson(req.body(), User.class);

            userDao.add(user);
            res.status(201);
            res.type("application/json");
            return gson.toJson(userDao.getAll());
        });

        post("/news/new", "application/json",(req,res)->{
            News news=gson.fromJson(req.body(), News.class);


            newsDao.add(news);
            res.status(201);
            res.type("application/json");
            return gson.toJson(newsDao.getAll());

        });
        post("department/new","application/json", (request,response)->{
            Department department= gson.fromJson(request.body(), Department.class);
            departmentDao.add(department);

            response.type("application/json");
            response.status(201);
            return gson.toJson(departmentDao.getAll());
        });

        post("/department/:departmentId/news/:newsId","application/json",(req,res)->{
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

        post("/department/:departmentId/users/:userId","application/json",(req,res)->{
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

        get("/department", (req,res)->{
            res.type("application/json");
            return  gson.toJson(departmentDao.getAll());
        });

        get("/department/:id",(req,res)->{
            res.type("application/json");
            int departmentId = Integer.parseInt(req.params("id"));
            res.type("application/json");
            return gson.toJson(departmentDao.findById(departmentId));
        });

        get("/department/:id/users",(req,res)->{
            res.type("application/json");
            int departmentId = Integer.parseInt(req.params("id"));
            res.type("application/json");
            return gson.toJson(departmentDao.findById(departmentId));
        });

        get("/department/:id/news",(req,res)->{
            res.type("application/json");
            int departmentId = Integer.parseInt(req.params("id"));
            res.type("application/json");
            return gson.toJson(departmentDao.findById(departmentId));
        });

        get("/news", (req,res)->{
            res.type("application/json");
            return  gson.toJson(newsDao.getAll());
        });

        get("news/:id",(req,res)->{
            res.type("application/json");
            int newsId=Integer.parseInt(req.params("id"));
            res.type("application/json");
            return gson.toJson(newsDao.findById(newsId));
        });

    }
}
