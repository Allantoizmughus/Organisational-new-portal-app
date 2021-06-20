package Dao;

import model.User;

import java.util.List;

public interface UserDao {
    //CREATE
    void add(User user);

    //READ
    List<User> getAll();
    User findById(int id);

    //UPDATE
    void update(int id,String name,String position,String role,int departmentId);

    //DELETE
    void clearAll();
}
