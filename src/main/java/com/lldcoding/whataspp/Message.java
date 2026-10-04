package com.lldcoding.whataspp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class Message {
    int id;
    String content;
    User sender;
    User receiver;
    LocalDateTime timestamp;
}
