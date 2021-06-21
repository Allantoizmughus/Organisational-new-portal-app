package Dao;

import model.Department;
import model.News;

import java.util.List;

public interface NewsDao {
    //CREATE
    void add(News news);
    void addDepartmentNews(News news, Department department);

    //READ
    List<News> getAll();
    News findById(int id);

    //UPDATE
    void update(News news,String content,int userId);

    //DELETE
    void clearAll();
}
