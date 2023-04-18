package com.gmail.sge.serejka;

import java.util.ArrayList;
import java.util.List;

public class JsonMessages {
    private final List<Message> list;

    public JsonMessages(List<Message> sourceList, int fromIndex, String name) {
        this.list = new ArrayList<>();
        for (int i = fromIndex; i < sourceList.size(); i++) {
            Message message = sourceList.get(i);
            String temp = message.getTo();
            String roomName = "";
            if (temp != null && temp.contains("room")){
                String [] array = temp.split(" ");
                roomName = array[1];
            }
            Room room = RoomList.getInstance().getRoomByName(roomName);
            if (room != null){
                if (room.checkUserInRoom(name)){
                    list.add(sourceList.get(i));
                }
            } else if (message.getTo() == null || message.getTo().equals(name) || message.getFrom().equals(name)) {
                list.add(sourceList.get(i));
            }
        }
    }
}