package com.gmail.sge.serejka;

import java.util.ArrayList;
import java.util.List;

public class RoomList {
    private static final RoomList roomList= new RoomList();
    private static final List<Room> list = new ArrayList<>();

    private RoomList(){
        super();
    }

    public static RoomList getInstance(){
        return roomList;
    }

    public void createNewRoom(String name){
        list.add(new Room(name));
    }

    public Room getRoomByName(String name){
        for (Room room : list){
            if (room.getName().equals(name)){
                return room;
            }
        }
        return null;
    }

}
