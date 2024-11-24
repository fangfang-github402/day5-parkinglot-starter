package com.parkinglot;

import java.util.List;

public class ParkingBoyStrategy implements ParkStrategy {
    @Override
    public Ticket park(List<ParkingLot> parkingLots, Car car) {
        for (int i = 0; i < parkingLots.size(); i++) {
            try {
                Ticket ticket = parkingLots.get(i).park(car);
                System.out.println("The car:" + car.getId() + " has parked in ParkingLot:" + (i + 1));
                return ticket;
            } catch (Exception e) {
            }
        }
        throw new NoAvailablePositionExpection();
    }
}
