package Dao;

import model.Department;
import model.User;

import java.util.List;

public interface UserDao {
    //CREATE
    void add(User user);
    void addDepartmentUser(User user, Department department);

    //READ
    List<User> getAll();
    User findById(int id);

    //UPDATE
    void update(User user,int id,String name,String position,String role,int departmentId);

    //DELETE
    void clearAll();
}
