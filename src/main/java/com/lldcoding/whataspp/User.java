package com.lldcoding.whataspp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedList;

@Getter
@Setter
@AllArgsConstructor
public class User {
    private int id;
    private String name;
    // user send messages to other users and receive messages from other users
    private HashMap<String, LinkedList<Message>> messages = new HashMap<>();



    // send message to another user
    public void sendMessage(User receiver, String content) {
        Message message = new Message(1, content, this, receiver, LocalDateTime.now());
        // add message to sender's messages
        if (!this.messages.containsKey(receiver.getName())) {
            this.messages.put(receiver.getName(), new LinkedList<>());
        }
        // send message to receiver
        receiver.receiveMessage(this, message);
        System.out.println("Message sent from " + this.name + " to " + receiver.name + ": " + content);
    }

    // receive message from another user (add message to receiver's messages)
    public void receiveMessage(User sender, Message message) {
        // add message to receiver's messages
        if (!this.messages.containsKey(sender.getName())) {
            this.messages.put(sender.getName(), new LinkedList<>());
        }
        this.messages.get(sender.getName()).add(message);
        System.out.println("Message received by " + this.name + " from " + sender.name + ": " + message.getContent());
    }
}
