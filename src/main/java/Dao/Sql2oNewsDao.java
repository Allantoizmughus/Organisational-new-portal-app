package Dao;

import model.Department;
import model.News;
import model.User;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oNewsDao implements NewsDao {
    private final Sql2o sql2o;

    public Sql2oNewsDao(Sql2o sql2o){
        this.sql2o=sql2o;
    }

    @Override
    public void add(News news) {
        String sql="INSERT INTO news(content,userId) VALUES(:content,:userId)";
        try(Connection con = sql2o.open()){
            int id = (int) con.createQuery(sql,true)
//                    .addParameter("id",news.getId())
//                    .addParameter("content",news.getContent())
//                    .addParameter("userId",news.getUserId())
                    .bind(news)
                    .executeUpdate().getKey();
            news.setId(id);
        }
    }

    @Override
    public List<News> getAll() {
        String sql="SELECT * FROM news";
        try(Connection con = sql2o.open()){
            return con.createQuery(sql).executeAndFetch(News.class);
        }
    }

    @Override
    public News findById(int id) {
        String sql ="SELECT * FROM news WHERE id = :id ";
        try(Connection con = sql2o.open()){
            return con.createQuery(sql).addParameter("id",id).executeAndFetchFirst(News.class);
        }
    }

    @Override
    public void addDepartmentNews(News news, Department department){
        String sql = "INSERT INTO department_news (departmentId, newsId) VALUES (:departmentId, :newsId)";
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
    public void update(News news, int id, String content, int userId) {
        String sql="UPDATE news SET(content,userId)=(:content,:userId) WHERE id=:id";
        try(Connection con = sql2o.open()){
            con.createQuery(sql)
                    .addParameter("id",id)
                    .addParameter("content",content)
                    .addParameter("userId",userId)
                    .executeUpdate();
            news.setContent(content);
            news.setUserId(userId);
        }
    }

    @Override
    public void clearAll() {
        String sql = "DELETE FROM news";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql).executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }
}
