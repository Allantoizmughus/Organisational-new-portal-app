package Dao;

import model.DB;
import org.junit.rules.ExternalResource;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

public class DatabaseRule extends ExternalResource {
    @Override
    protected void before() {
        DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/news_portal", "moringa", "Access");
    }

    @Override
    protected void after() {
        try (Connection con = DB.sql2o.open()) {
            String deleteUsersQuery = "DELETE FROM users *;";
            String deleteDepartmentQuery = "DELETE FROM department *;";
            String deleteNewsQuery = "DELETE FROM news *;";
            con.createQuery(deleteUsersQuery).executeUpdate();
            con.createQuery(deleteDepartmentQuery).executeUpdate();
            con.createQuery(deleteNewsQuery).executeUpdate();
        }
    }
}
