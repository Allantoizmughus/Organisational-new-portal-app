package Dao;

import model.Department;
import model.News;
import model.User;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.ArrayList;
import java.util.List;

public class Sql2oDepartmentDao implements DepartmentDao{
    private final Sql2o sql2o;

    public Sql2oDepartmentDao(Sql2o sql2o){
        this.sql2o=sql2o;
    }


    @Override
    public void add(Department department) {
        String sql = "INSERT INTO departments (id,name, description) VALUES (:id,:name, :description)";
        try (Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql, true)
                    .bind(department)
                    .executeUpdate()
                    .getKey();
            department.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void addDepartmentNews(Department department, News news) {
        String sql = "INSERT INTO departmentNews (departmentId, newsId) VALUES (:departmentId, :newsId)";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("departmentId", department.getId())
                    .addParameter("newsId", news.getId())
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public void addDepartmentUser(Department department, User user) {
        String sql = "INSERT INTO department_news (departmentId, userId) VALUES (:departmentId, :userId)";
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
    public List<Department> getAll() {
            try (Connection con = sql2o.open()) {
                return con.createQuery("SELECT * FROM departments")
                        .executeAndFetch(Department.class);
            }
    }

    @Override
    public List<News> getAllDepartmentNews(int departmentId) {
        List<News> news = new ArrayList(); //empty list
        String joinQuery = "SELECT newsId FROM departmentNews WHERE departmentId = :departmentId";

        try (Connection con = sql2o.open()) {
            List<Integer> allNewsIds = con.createQuery(joinQuery)
                    .addParameter("departmentId", departmentId)
                    .executeAndFetch(Integer.class);
            for (Integer newsId : allNewsIds){
                String newsQuery = "SELECT * FROM news WHERE id = :newsId";
                news.add(
                        con.createQuery(newsQuery)
                                .addParameter("newsId", newsId)
                                .executeAndFetchFirst(News.class));
            }
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
        return news;
    }



    @Override
    public List<User> getAllDepartmentUser(int departmentId) {
        List<User> user = new ArrayList(); //empty list
        String joinQuery = "SELECT userId FROM departmentUsers WHERE departmentId = :departmentId";

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
    public Department findById(int id) {
        return null;
    }

    @Override
    public void update(int id, String name, String description) {

    }

    @Override
    public void clearAll() {

    }
}
