package com.lldcoding.irstc;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Seat {
    private int seatNumber;
    private SeatStatus seatStatus;
}
