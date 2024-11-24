package com.parkinglot;

import java.util.List;

public class ParkingBoyStrategy implements ParkStrategy {
    @Override
    public Ticket park(List<ParkingLot> parkingLots, Car car) {
        ParkingLot parkingLot = parkingLots.stream()
                .filter(lot -> lot.getNumberOfEmptyPositions() != 0)
                .findFirst().orElse(null);
        if (parkingLot == null) {
            throw new NoAvailablePositionExpection();
        }
        System.out.println("The car:" + car.getId() + " has parked in ParkingLot:" + (parkingLots.indexOf(parkingLot) + 1));
        return parkingLot.park(car);
    }
}
