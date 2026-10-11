package com.lldcoding.irstc;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class User {
    private String name;
    private String email;
    private String phoneNumber;
    private Set<Ticket> tickets;
}
