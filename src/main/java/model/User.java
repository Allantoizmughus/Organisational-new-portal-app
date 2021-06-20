package model;

import java.util.ArrayList;
import java.util.Objects;

public class User {
    private int id;
    private String name;
    private String position;
    private String role;
    private int departmentId;
    private static ArrayList<User> instances=new ArrayList<>();

    public User(int id,String name,String position,String role,int departmentId) {
        this.id=instances.size();
        this.name=name;
        this.position=position;
        this.role=role;
        this.departmentId=departmentId;
        instances.add(this);

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static User setUpNewUser(){
        return new User(0, "Allan", "Secretary", "Admin", 0);
    }

    public static ArrayList<User> getAllInstances() {return instances;}


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return id == user.id && departmentId == user.departmentId && name.equals(user.name) && position.equals(user.position) && role.equals(user.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, position, role, departmentId);
    }
}
