package Dao;

import junit.framework.TestCase;
import model.Department;
import model.User;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Sql2oUserDaoTest {

    private static Connection conn;
    private static Sql2oUserDao userDao;
    private static Sql2oDepartmentDao departmentDao;
    private static Sql2oNewsDao newsDao;
    private static User user;

    public void setUp() throws Exception {
        String connectionString = "jdbc:postgresql://localhost:5432/news_portal";
        Sql2o sql2o = new Sql2o(connectionString, "moringa", "Access");
        departmentDao = new Sql2oDepartmentDao(sql2o);
        newsDao = new Sql2oNewsDao(sql2o);
        userDao = new Sql2oUserDao(sql2o);
        conn = sql2o.open();
    }

    public void tearDown() throws Exception {
        userDao.clearAll();
        newsDao.clearAll();
        departmentDao.clearAll();
    }

    @Test
    public void returnAllDepartmentUser(){
        User newUser  = new User( 0,"Allan", "Secretary", "Admin", 0);
        userDao.add(newUser);

        User otherUser  = new User(0,"Greg","Cook","Cooking",1);
        userDao.add(otherUser);

        Department newDepartment = setupDepartment();
        departmentDao.add(newDepartment);
        userDao.addDepartmentUser(newUser,newDepartment);
        userDao.addDepartmentUser(otherUser,newDepartment);

        User[] user = {newUser, otherUser};

        assertEquals(Arrays.asList(user), userDao.getAllDepartmentUser(newDepartment.getId()));
    }

    @Test
    public void returnUserId(){
        User newUser = setupNewUsers();
        int originalUsersId = newUser.getId();
        userDao.add(newUser);

        assertEquals(originalUsersId,newUser.getId());
    }

    @Test
    public void getAllUser(){
        User newUser=setupNewUsers();
        User otherUser=setupNewUsers();
        userDao.add(newUser);
        userDao.add(otherUser);

        assertEquals(otherUser,userDao.getAll().size());
        assertTrue(userDao.getAll().containsAll(Arrays.asList(newUser,otherUser)));
    }

    @Test
    public void clearAllUser(){
        User newUser = setupNewUsers();
        User otherUser = setupNewUsers();
        userDao.clearAll();
        assertEquals(0, userDao.getAll().size());
    }

    @Test
    public void findUserById(){
        User newUser = setupNewUsers();
        User otherUser = setupNewUsers();
        userDao.add(newUser);
        userDao.add(otherUser);
        assertEquals(newUser,userDao.findById(newUser.getId()));
    }

    @Test
    public void updateUserCorrectly(){
        User newUser = setupNewUsers();
        userDao.add(newUser);
        userDao.update(newUser,0,"Greg","Cook","Hospitality",0);
        User foundUser=userDao.findById(newUser.getId());
        assertEquals("Greg",foundUser.getName());
        assertEquals("Cook",foundUser.getPosition());
        assertEquals("Hospitality",foundUser.getRole());
        assertEquals(0,foundUser.getDepartmentId());

    }

    public User setupNewUsers() {
        User newUser = new User(0,"Allan", "Secretary", "Admin", 0);
        userDao.add(newUser);
        return newUser;
    }

    public Department setupDepartment() {
        Department newDepartment=new Department(0,"Security","Offer Security");
        departmentDao.add(newDepartment);
        return newDepartment;
    }


}