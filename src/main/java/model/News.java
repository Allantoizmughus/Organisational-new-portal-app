package model;

import java.security.Timestamp;
import java.util.ArrayList;

public class News {
    private int id;
    private String content;
    private int userId;
    private int departmentId;
    private Timestamp createdAt;
    private static ArrayList<News> instances=new ArrayList<>();

    public News(int id,String content,int userId,int departmentId) {
        this.id= instances.size();
        this.content = content;
        this.userId=userId;
        this.departmentId=departmentId;
        this.createdAt=createdAt;
        instances.add(this);

    }

    public News(int id,String content,int userId) {
        this.id=id;
        this.content = content;
        this.userId=userId;
        this.createdAt=createdAt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

//    public Timestamp getCreatedAt() {
//        return createdAt;
//    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public static ArrayList<News> getAllInstances() {return instances;}

    public static News setUpNewNews(){
        return new News(0,"Holiday",0,0);
    }

}
