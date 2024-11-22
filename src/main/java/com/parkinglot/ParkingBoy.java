package com.parkinglot;

import java.util.ArrayList;
import java.util.List;

public class ParkingBoy {
    private ParkingLot parkingLot;
    private List<ParkingLot> parkingLots;

    public ParkingBoy() {
        this.parkingLots = new ArrayList<>();
    }

    public Ticket park(Car car) {
        String exceptionMsg = null;
        for (int i = 0; i < parkingLots.size(); i++) {
            try {
                Ticket ticket = parkingLots.get(i).park(car);
                System.out.println("The car has parked in ParkingLot:" + (i + 1));
                return ticket;
            } catch (Exception e) {
                exceptionMsg = e.getMessage();
            }
        }
        System.out.println(exceptionMsg);
        return null;
    }

    public Car fetch(Ticket ticket) {
        return parkingLot.fetch(ticket);
    }

    public void addParkingLot(ParkingLot parkingLot) {
        this.parkingLots.add(parkingLot);
    }
}
