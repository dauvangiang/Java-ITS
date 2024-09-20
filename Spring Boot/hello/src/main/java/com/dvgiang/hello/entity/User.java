package com.dvgiang.hello.entity;

public class User {
    private String id;
    private String name;
    private String phone;

    public User(String name, String phone, String id){
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public String getName(){
        return name;
    }

    public String getPhone(){
        return phone;
    }
}
