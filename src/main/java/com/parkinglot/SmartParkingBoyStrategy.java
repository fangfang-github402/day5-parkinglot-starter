package com.parkinglot;

import java.util.Comparator;
import java.util.List;

public class SmartParkingBoyStrategy implements ParkStrategy {
    @Override
    public Ticket park(List<ParkingLot> parkingLots, Car car) {
        ParkingLot parkingLot = parkingLots.stream().max(Comparator.comparing(ParkingLot::getNumberOfEmptyPositions)).orElse(null);
        if (parkingLot == null) {
            throw new NoAvailablePositionExpection();
        }
        System.out.println("The car:" + car.getId() + " has parked in ParkingLot:" + (parkingLots.indexOf(parkingLot) + 1));
        return parkingLot.park(car);
    }
}
