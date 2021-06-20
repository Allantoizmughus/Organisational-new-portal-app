package Dao;

import model.Department;
import model.News;
import model.User;

import java.util.List;

public interface DepartmentDao {
    //CREATE
    void add(Department department);
    void addDepartmentNews(Department department, News news);
    void addDepartmentUser(Department department, User user);

    //READ
    List<Department> getAll();
    List<News>getAllDepartmentNews();
    List<User>getAllDepartmentUser();
    Department findById(int id);

    //UPDATE
    void update(int id,String name,String description);

    //CREATE
    void clearAll();
}
