package Dao;

import junit.framework.TestCase;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

public class Sql2oUserDaoTest {

    private static Connection conn;
    private static Sql2oUserDao usersDao;
    private static Sql2oDepartmentDao departmentDao;
    private static Sql2oNewsDao newsDao;

    public void setUp() throws Exception {
        String connectionString = "jdbc:postgresql://localhost:5432/news_api_test";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        departmentDao = new Sql2oDepartmentDao(sql2o);
        newsDao = new Sql2oNewsDao(sql2o);
        usersDao = new Sql2oUserDao(sql2o);
        conn = sql2o.open();
    }

    public void tearDown() throws Exception {
        conn.close();
    }
}