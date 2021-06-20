package model;

import java.util.ArrayList;

public class Department {
    private int id;
    private String name;
    private String description;
    private int memberNumber;
    private ArrayList<User> departmentUser;
    private ArrayList<News> departmentNews;
    private static ArrayList<Department> instances=new ArrayList<>();



    public Department(String name,String description) {
        this.name=name;
        this.description=description;
        this.memberNumber=memberNumber;
        this.id= instances.size();
        this.departmentUser=new ArrayList<>();
        this.departmentNews=new ArrayList<>();
        instances.add(this);

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMemberNumber() {
        return memberNumber;
    }

    public void setMemberNumber(int memberNumber) {
        this.memberNumber = memberNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<User> getDepartmentUser() {
        return departmentUser;
    }

    public void setDepartmentUser(User newMember) {
        departmentUser.add(newMember);
    }

    public ArrayList<News> getDepartmentNews() {
        return departmentNews;
    }

    public void setDepartmentNews(ArrayList<News> departmentNews) {
        this.departmentNews = departmentNews;
    }

    public static ArrayList<Department> getAllInstances(){return instances;}

    public static Department setUpNewDepartment(){
        return new Department("Admin","Registering");
    }

}
