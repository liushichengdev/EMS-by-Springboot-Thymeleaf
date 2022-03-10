package com.springboot.ems.entity;

import java.util.Date;

public class Employee {
    private int id;
    private String name;
    private double salary;
    private Date birthday;
    private String photo;

    public Employee() {
    }

    public Employee(int id, String name, double salary, Date birthday, String photo) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.birthday = birthday;
        this.photo = photo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
