package com.lldcoding.rapido;

import java.security.SecureRandom;
import java.util.List;

public class RapidoFacade {
    public static void main(String args[]){
        // create -> manage -> complete/notify
        SecureRandom random = new SecureRandom();

        Driver driver1 = new Driver(random.nextInt(),"red", 4, DriverStatus.FREE);
        Driver driver2 = new Driver(random.nextInt(),"blue", 10, DriverStatus.FREE);
        Driver driver3 = new Driver(random.nextInt(),"green", 10, DriverStatus.OFFLINE);
        List<Driver> driverList = List.of(driver1, driver3, driver2);

        Rider rider1 = new Rider(random.nextInt(),"rose");

        AllocationService allocationService = new AllocationService();
        Ride ride1 = new Ride(random.nextInt(),rider1, 9);
        ride1 = allocationService.findDriver(ride1, driverList);
        System.out.println("driver assigned "+ride1.getAssignedDriver().getName());

    }
}
