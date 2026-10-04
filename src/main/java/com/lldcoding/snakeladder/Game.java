package com.lldcoding.snakeladder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class Game {
    private final int id;
    private Board board;
    private Set<Player> players;
}
