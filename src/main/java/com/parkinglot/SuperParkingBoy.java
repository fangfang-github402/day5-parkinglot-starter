package com.parkinglot;

import java.util.Comparator;

public class SuperParkingBoy extends ParkingBoy {

    @Override
    public Ticket park(Car car) {
        ParkingLot parkingLot = parkingLots.stream().max(Comparator.comparing(ParkingLot::getAvailablePositionRate)).orElse(null);
        if (parkingLot == null) {
            throw new NoAvailablePositionExpection();
        }
        System.out.println("The car:" + car.getId() + " has parked in ParkingLot:" + (parkingLots.indexOf(parkingLot) + 1));
        Ticket ticket = parkingLot.park(car);
        return ticket;
    }

}
