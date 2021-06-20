package Dao;

import model.News;
import model.User;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

public class Sql2oNewsDao implements NewsDao {
    private final Sql2o sql2o;

    public Sql2oNewsDao(Sql2o sql2o){
        this.sql2o=sql2o;
    }

    @Override
    public void add(News news) {
        String sql="INSERT INTO news(id,content,userId) VALUES(:id,:content,:userId)";
        try(Connection con = sql2o.open()){
            int id = (int) con.createQuery(sql,true)
                    .addParameter("id",news.getId())
                    .addParameter("content",news.getContent())
                    .addParameter("userId",news.getUserId())
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
    public void update(News news, int id, String content, int userId) {
        String sql="UPDATE news SET(id,content,userId)=(:id,:content,:userId)";
        try(Connection con = sql2o.open()){
            con.createQuery(sql)
                    .addParameter("id",id)
                    .addParameter("content",content)
                    .addParameter("userId",userId)
                    .executeUpdate();
            news.setId(id);
            news.setContent(content);
            news.setUserId(userId);
        }
    }

    @Override
    public void clearAll() {

    }
}
