package com.gmail.sge.serejka;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.LinkedList;
import java.util.List;

public class MessageList {
    private static final MessageList msglist = new MessageList();
    private static final int LIMIT = 100;
    private final Gson gson;
    private final List<Message> list = new LinkedList<Message>();

    public static MessageList getInstance() {
        return msglist;
    }

    private MessageList(){
        gson = new GsonBuilder().create();
    }

    public synchronized void add(Message m){
        if (list.size() + 1 == LIMIT) {
            list.remove(0);
        }
        list.add(m);
    }

    public synchronized String toJSON(int n, String name){
        if (n == list.size()){
            return null;
        }
        return gson.toJson(new JsonMessages(list, n, name));
    }
}