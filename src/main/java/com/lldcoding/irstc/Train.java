package com.lldcoding.irstc;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Train {
    private int trainNumber;
    private String trainName;
    private List<Seat> seats;
    private TrainStatus trainStatus;
}
