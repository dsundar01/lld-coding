package com.lldcoding.tictoc;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Player {
    private String name;
    private char symbol; // 'X' or 'O'
}