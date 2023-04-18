package com.gmail.sge.serejka;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class User {
    private String name;
    private String password;
    private boolean active;

    public User(String name, String password, boolean active) {
        this.name = name;
        this.password = password;
        this.active = active;
    }

    public User() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String toJson(){
        Gson gson = new GsonBuilder().create();
        return gson.toJson(this);
    }

    public static User fromJson(String json){
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(json, User.class);
    }

    @Override
    public String toString() {
        return "User{" +
                " name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", active=" + active +
                '}';
    }
}