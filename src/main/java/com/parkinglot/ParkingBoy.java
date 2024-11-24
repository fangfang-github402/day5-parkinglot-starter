package com.parkinglot;

import java.util.ArrayList;
import java.util.List;

public class ParkingBoy {
    private ParkingLot parkingLot;
    protected List<ParkingLot> parkingLots;

    public ParkingBoy() {
        this.parkingLots = new ArrayList<>();
    }

    public Ticket park(Car car) {
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

    public Car fetch(Ticket ticket) {
        for (int i = 0; i < parkingLots.size(); i++) {
            try {
                Car car = parkingLots.get(i).fetch(ticket);
                return car;
            } catch (Exception e) {
            }
        }
        throw new UnrecognizedParkingTicketException();
    }

    public void addParkingLot(ParkingLot parkingLot) {
        this.parkingLots.add(parkingLot);
    }
}
