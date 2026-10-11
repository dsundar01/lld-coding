package com.lldcoding.irstc;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class RailOne {
    private Set<Train> trains;
    private Set<User> users;
}
