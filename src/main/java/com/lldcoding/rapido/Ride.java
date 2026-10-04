package com.lldcoding.rapido;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
public class Ride {
    private int id;
    private Driver assignedDriver;
    private Rider rider;
    private LocalDateTime rideStartTime;
    private LocalDateTime rideEndTime;
    private double fare;
    private int location;

    public Ride(int id, Rider rider1, int location) {
        this.id = id;
        this.rider = rider1;
        this.location = location;
    }
}
