package Dao;

import model.News;
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
        return null;
    }

    @Override
    public News findById(int id) {
        return null;
    }

    @Override
    public void update(News news, int id, String content, int userId) {

    }

    @Override
    public void clearAll() {

    }
}
