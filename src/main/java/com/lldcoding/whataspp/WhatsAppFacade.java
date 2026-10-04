package com.lldcoding.whataspp;

import java.util.HashMap;

public class WhatsAppFacade {
    public static void main(String[] args) {

        //load data
        User user = new User(1, "John", new HashMap<>());
        User user2 = new User(2, "Jane", new HashMap<>());

        // execute send message
        user.sendMessage(user2, "Hello Jane!");
        user2.sendMessage(user, "Hello John! How are");

        // print messages
        System.out.println("Messages for " + user.getName() + ":");
        user.getMessages().forEach((k, v) -> {
            System.out.println("Messages with " + k + ":");
            v.forEach(m -> System.out.println(m.getContent()));
        });

    }
}
