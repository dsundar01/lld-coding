package com.lldcoding.rapido;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Driver extends User{

    private int currentLocation;
    private int id;
    private String name;
    private DriverStatus status;

    public Driver(int id, String name, int loc, DriverStatus status){
        this.name = name;
        this.currentLocation = loc;
        this.status = status;
        this.id = id;
    }

}
