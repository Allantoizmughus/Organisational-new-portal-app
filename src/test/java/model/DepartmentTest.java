package model;

import junit.framework.TestCase;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DepartmentTest {

    public void setUp() throws Exception {
    }

    public void tearDown() throws Exception {
    }

    @Test
    public void returnInstancesOfDepartments_true(){
        Department newDepartment=new Department(0,"Admin","Registering");
        assertEquals(true,newDepartment instanceof Department);
    }

    @Test
    public void returnIdOfDepartment_int(){
        Department newDepartment=new Department(0,"Admin","Registering");
        assertEquals(0,newDepartment.getId());
    }

    @Test
    public void returnNameOfDepartment_String(){
        Department newDepartment=new Department(0,"Admin","Registering");
        assertEquals("Admin",newDepartment.getName());
    }

    @Test
    public void returnDescriptionOfDepartment_String(){
        Department newDepartment=new Department(0,"Admin","Registering");
        assertEquals("Registering",newDepartment.getDescription());
    }

    @Test
    public void newDepartment_getAllInstances_true(){
        Department newDepartment=Department.setUpNewDepartment();
        assertTrue(Department.getAllInstances().contains(newDepartment));
    }

    @Test
    public void newDepartment_getDepartmentUsers_Array(){
        Department newDepartment=Department.setUpNewDepartment();
        User newUser=User.setUpNewUser();
        newDepartment.setDepartmentUser(newUser);
        assertEquals("Allan",newDepartment.getDepartmentUser().get(0).getName());

    }

    @Test
    public void clearAllDepartmentsCorrectly_0(){
        Department.clearAllDepartment();
        assertEquals(Department.getAllInstances().size(),0);
    }
}