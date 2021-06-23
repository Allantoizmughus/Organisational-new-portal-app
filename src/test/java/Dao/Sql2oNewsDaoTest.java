package Dao;

import junit.framework.TestCase;
import model.Department;
import model.News;
import model.User;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Sql2oNewsDaoTest {
    private static Connection conn;
    private static Sql2oNewsDao newsDao;
    private static Sql2oDepartmentDao departmentDao;
    private static Sql2oUserDao userDao;

    public void setUp() throws Exception {
        String connectionString = "jdbc:postgresql://localhost:5432/news_portal";
        Sql2o sql2o = new Sql2o(connectionString, "moringa", "Access");
        departmentDao = new Sql2oDepartmentDao(sql2o);
        newsDao = new Sql2oNewsDao(sql2o);
        userDao = new Sql2oUserDao(sql2o);
        conn = sql2o.open();
    }

    public void tearDown() throws Exception {
        newsDao.clearAll();
        userDao.clearAll();
        departmentDao.clearAll();
    }

    @Test
    public void getAllNews(){
        News firstNews=setUpNewNews();
        News secondNews=setUpNewNews();
        newsDao.add(firstNews);
        newsDao.add(secondNews);
        assertEquals(secondNews,newsDao.getAll().size());
        assertTrue(newsDao.getAll().containsAll(Arrays.asList(firstNews,secondNews)));

    }

    @Test
    public void returnNewsId(){
        News firstNews=setUpNewNews();
        int originalNewsId= firstNews.getId();
        newsDao.add(firstNews);
        assertEquals(originalNewsId,firstNews.getId());
    }

    @Test
    public void clearAllNews(){
        News firstNews=setUpNewNews();
        News secondNews=setUpNewNews();
        newsDao.clearAll();
        assertEquals(0,newsDao.getAll().size());
    }

    @Test
    public void findNewsById(){
        News firstNews=setUpNewNews();
        News secondNews=setUpNewNews();
        newsDao.add(firstNews);
        newsDao.add(secondNews);
        assertEquals(firstNews,newsDao.findById(firstNews.getId()));
    }

    @Test
    public void updateNewsCorrectly(){
        News firstNews=setUpNewNews();
        newsDao.add(firstNews);
        newsDao.update(firstNews,0,"Health",1);
        News foundNews=newsDao.findById(firstNews.getId());
        assertEquals("Health",foundNews.getContent());
        assertEquals(1,foundNews.getUserId());
    }


    public News setUpNewNews() {
        News firstNews=new News(0,"Holiday",0,0);
        newsDao.add(firstNews);
        return firstNews;
    }

    public Department setupDepartment() {
        Department newDepartment=new Department(0,"Security","Offer Security");
        departmentDao.add(newDepartment);
        return newDepartment;
    }
}