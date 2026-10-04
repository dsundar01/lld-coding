package com.lldcoding.rapido;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Rider {
    private int id;
    private String name;

    public Rider(int id, String name){
        this.name = name;
        this.id = id;
    }
}
