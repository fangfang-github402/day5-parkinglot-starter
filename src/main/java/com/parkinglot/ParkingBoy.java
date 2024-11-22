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
        String parkingExceptionMsg = null;
        for (int i = 0; i < parkingLots.size(); i++) {
            try {
                Ticket ticket = parkingLots.get(i).park(car);
                System.out.println("The car has parked in ParkingLot:" + (i + 1));
                return ticket;
            } catch (Exception e) {
                parkingExceptionMsg = e.getMessage();
            }
        }
        throw new NoAvailablePositionExpection(parkingExceptionMsg);
    }

    public Car fetch(Ticket ticket) {
        String fetchingExceptionMsg = null;
        for (int i = 0; i < parkingLots.size(); i++) {
            try {
                Car car = parkingLots.get(i).fetch(ticket);
                return car;
            } catch (Exception e) {
                fetchingExceptionMsg = e.getMessage();
            }
        }
        throw new UnrecognizedParkingTicketException(fetchingExceptionMsg);
    }

    public void addParkingLot(ParkingLot parkingLot) {
        this.parkingLots.add(parkingLot);
    }
}
