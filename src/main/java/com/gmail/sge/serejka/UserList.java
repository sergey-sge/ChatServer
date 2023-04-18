package com.gmail.sge.serejka;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

public class UserList {
    private static final UserList userList = new UserList();
    private static List<User> list = new ArrayList<>();
    private Gson gson;

    private UserList() {
        gson = new GsonBuilder().create();
    }

    public static UserList getInstance() {
        return userList;
    }

    public synchronized void add(User user) {
        list.add(user);
    }

    public User authorizedUser(User checkUser){
        for (User user : list){
            if (user.getName().equals(checkUser.getName())){
                if (user.getPassword().equals(checkUser.getPassword())){
                    user.setActive(true);
                    return user;
                } else {
                    return checkUser;
                }
            }
        }
        add(checkUser);
        checkUser.setActive(true);
        return checkUser;
    }


    public List<String> activeUsers(){
        List<String> activeList = new ArrayList<>();
        for (User user : list){
            if (user.isActive()){
                activeList.add(user.getName());
            }
        }
        return activeList;
    }



    public User getUserByName(String name){
        for (User u : list){
            if (u.getName().equals(name)){
                return u;
            }
        }
        return null;
    }

    public String getUserByNameToJSON(String name){
        return gson.toJson(getUserByName(name));
    }
}