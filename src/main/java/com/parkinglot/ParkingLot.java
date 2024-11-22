package com.parkinglot;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private Map<Ticket, Car> parkingRecords = new HashMap<>();

    public Ticket park(Car car) {
        Ticket ticket = new Ticket();
        if (parkingRecords.size() < 10) {
            parkingRecords.put(ticket, car);
            return ticket;
        }
        return null;
    }

    public Car fetch(Ticket ticket) {
        Car fetchedCar = parkingRecords.getOrDefault(ticket, null);
        parkingRecords.remove(ticket);
        return fetchedCar;
    }
}
