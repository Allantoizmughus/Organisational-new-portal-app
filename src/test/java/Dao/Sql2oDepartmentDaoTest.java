package Dao;


import model.Department;
import model.News;
import model.User;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class Sql2oDepartmentDaoTest {

    private static Connection conn;
    private static Sql2oDepartmentDao departmentDao;
    private static Sql2oNewsDao newsDao;
    private static Sql2oUserDao userDao;

    public void setUp() throws Exception {
        String connectionString = "jdbc:postgresql://localhost:5432/news_api_test";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        departmentDao = new Sql2oDepartmentDao(sql2o);
        newsDao = new Sql2oNewsDao(sql2o);
        userDao = new Sql2oUserDao(sql2o);
        conn = sql2o.open();
    }

    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void addDepartment(){
        Department newDepartment = setupDepartment();
        assertEquals(0,departmentDao.getAll().size());
    }

    @Test
    public void departmentReturnsEmptyList(){
        assertEquals(0, departmentDao.getAll().size());
    }

    @Test
    public void findByIdReturnsCorrectDepartment() throws Exception {
        Department newDepartment = setupDepartment();
        assertEquals(newDepartment, departmentDao.findById(newDepartment.getId()));
    }

    @Test
    public void getAllDepartments(){
        Department newDepartment = setupDepartment();
        Department newestDepartment = setupDepartment();
        assertEquals(newestDepartment, departmentDao.getAll().size());
    }

    @Test
    public void updateCorrectly(){
        Department newDepartment = setupDepartment();
        departmentDao.add(newDepartment);
        departmentDao.update(newDepartment.getId(), "Hospitality","Catering services");
        Department foundDepartment = departmentDao.findById(newDepartment.getId());
        assertEquals("Hospitality", foundDepartment.getName());
        assertEquals("Catering services", foundDepartment.getDescription());
    }

    @Test
    public void clearAllDepartments(){
        Department newDepartment = setupDepartment();
        Department newestDepartment = setupDepartment();
        departmentDao.clearAll();
        assertEquals(0, departmentDao.getAll().size());
    }

    @Test
    public void returnAllDepartmentNews(){
        News firstNews  = new News(0,"Holiday",0);
        newsDao.add(firstNews);

        News secondNews  = new News(1,"health",1);
        newsDao.add(secondNews);

        Department newDepartment = setupDepartment();
        departmentDao.add(newDepartment);
        departmentDao.addDepartmentNews(newDepartment,firstNews);
        departmentDao.addDepartmentNews(newDepartment,secondNews);

        News[] news = {firstNews, secondNews};

        assertEquals(Arrays.asList(news), departmentDao.getAllDepartmentNews(newDepartment.getId()));
    }

    @Test
    public void returnAllDepartmentUser(){
        User newUser  = new User( "Allan", "Secretary", "Admin", 0);
        userDao.add(newUser);

        User otherUser  = new User("Greg","Cook","Cooking",1);
        userDao.add(otherUser);

        Department newDepartment = setupDepartment();
        departmentDao.add(newDepartment);
        departmentDao.addDepartmentUser(newDepartment,newUser);
        departmentDao.addDepartmentUser(newDepartment,otherUser);

        User[] user = {newUser, otherUser};

        assertEquals(Arrays.asList(user), departmentDao.getAllDepartmentUser(newDepartment.getId()));
    }


    public Department setupDepartment() {
        Department newDepartment=new Department("Security","Offer Security");
        departmentDao.add(newDepartment);
        return newDepartment;
    }
}