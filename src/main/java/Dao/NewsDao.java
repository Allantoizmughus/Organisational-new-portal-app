package Dao;

import model.News;

import java.util.List;

public interface NewsDao {
    //CREATE
    void add(News news);

    //READ
    List<News> getAll();
    News findById(int id);

    //UPDATE
    void update(News news,int id,String content,int userId);

    //DELETE
    void clearAll();
}
