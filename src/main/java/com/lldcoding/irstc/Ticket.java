package com.lldcoding.irstc;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Ticket {
    private int ticketNumber;
    private User user;
    private Train train;
    private Seat seat;
    private TicketStatus ticketStatus;
}
