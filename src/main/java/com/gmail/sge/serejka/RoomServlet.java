package com.gmail.sge.serejka;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class RoomServlet extends HttpServlet {

    private RoomList roomList = RoomList.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String type = req.getParameter("type");
        String name = req.getParameter("name");
        String user = req.getParameter("user");
        String json = null;
        if (type.equals("create")) {
            if (roomList.getRoomByName(name) == null) {
                roomList.createNewRoom(name);
                roomList.getRoomByName(name).addUserToRoom(user);
                json = "The room " + name + " created";
            } else {
                json = "The room " + name + " already exists";
            }
        }
        if (type.equals("join")) {
            Room room = roomList.getRoomByName(name);
            if (room != null) {
                if (!room.checkUserInRoom(user)){
                    room.addUserToRoom(user);
                    json = user + " was added to room: " + name;
                } else {
                    json = "You are already in this room";
                }
            } else {
                json = "The room " + name + " doesn't exists";
            }
        }
        if (json != null){
            OutputStream os = resp.getOutputStream();
            byte[] buf = json.getBytes(StandardCharsets.UTF_8);
            os.write(buf);
        }
    }
}