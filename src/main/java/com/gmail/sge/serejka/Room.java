package com.gmail.sge.serejka;

import java.util.ArrayList;
import java.util.List;

public class Room {
    private List<String> usersInRoom;
    private String name;

    public Room (String name){
        this.name = name;
        usersInRoom = new ArrayList<>();
    }
    public Room(){
        super();
        usersInRoom = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean checkUserInRoom(String userName){
        for (String s : usersInRoom){
            if (s.equals(userName)){
                return true;
            }
        }
        return false;
    }

    public void addUserToRoom(String name){
        usersInRoom.add(name);
    }

    public List<String> getUsersInRoom(){
        return usersInRoom;
    }

}
