package model;

import java.util.ArrayList;

public class Department {
    private int id;
    private String name;
    private String description;
    private int memberNumber;
    private ArrayList<User> departmentUser;
    private ArrayList<News> departmentNews;



    public Department(int id,String name,String description,int memberNumber) {
        this.name=name;
        this.description=description;
        this.memberNumber=memberNumber;
        this.id=id;
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

    public void setDepartmentUser(ArrayList<User> departmentUser) {
        this.departmentUser = departmentUser;
    }

    public ArrayList<News> getDepartmentNews() {
        return departmentNews;
    }

    public void setDepartmentNews(ArrayList<News> departmentNews) {
        this.departmentNews = departmentNews;
    }
}
