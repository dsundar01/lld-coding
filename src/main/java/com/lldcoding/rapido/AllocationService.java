package com.lldcoding.rapido;

import lombok.AllArgsConstructor;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

@AllArgsConstructor
public class AllocationService {

    public Ride findDriver(Ride ride, List<Driver> drivers){

       int requestLoc = ride.getLocation();
       // find the nearest driver
        Driver nearestDriver = drivers.stream()
                .min(Comparator.comparingInt(driver -> Math.abs(driver.getCurrentLocation() - requestLoc)))
                .orElseThrow(() -> new NoSuchElementException("No drivers available"));
        ride.setAssignedDriver(nearestDriver);
        return ride;
    }
}
