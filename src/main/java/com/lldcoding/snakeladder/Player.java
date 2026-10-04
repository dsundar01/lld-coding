package com.lldcoding.snakeladder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Player {
    private final int id;
    private String name;
    private int currentPosition;
}
