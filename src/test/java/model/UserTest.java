package model;

import junit.framework.TestCase;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

public class UserTest {

    public void setUp() throws Exception {
    }

    public void tearDown() throws Exception {
    }

    @Test
    public void returnInstancesOfUser_true() {
        User newUser = new User(0, "Allan", "Secretary", "Admin", 0);
        assertEquals(true, newUser instanceof User);
    }

    @Test
    public void returnIdOfUser_int() {
        User newUser = new User(0, "Allan", "Secretary", "Admin", 0);
        assertEquals(0, newUser.getId());
    }

    @Test
    public void setIdOfUser_int(){
        User newUser = new User(0, "Allan", "Secretary", "Admin", 0);
        newUser.setId(0);
        assertNotEquals(0,newUser.getId());
    }

    @Test
    public void returnDepartmentId_int() {
        User newUser = new User(0, "Allan", "Secretary", "Admin", 0);
        assertEquals(0, newUser.getDepartmentId());

    }

    @Test
    public void returnNameOfUser_String() {
        User newUser = new User(0, "Allan", "Secretary", "Admin", 0);
        assertEquals("Allan", newUser.getName());

    }

    @Test
    public void returnPositionOfUser_String() {
        User newUser = new User(0, "Allan", "Secretary", "Admin", 0);
        assertEquals("Secretary", newUser.getPosition());
    }

    @Test
    public void returnRole_String() {
        User newUser = new User(0, "Allan", "Secretary", "Admin", 0);
        assertEquals("Admin", newUser.getRole());
    }

    @Test
    public void newUser_getAllInstances_true(){
        User newUser=User.setUpNewUser();
        assertTrue(User.getAllInstances().contains(newUser));
    }
}