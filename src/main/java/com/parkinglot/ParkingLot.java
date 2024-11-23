package com.parkinglot;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    public static final int MAX_CAPACITY = 10;
    private Map<Ticket, Car> parkingRecords = new HashMap<>();

    public Ticket park(Car car) {
        if (parkingRecords.size() < MAX_CAPACITY) {
            Ticket ticket = new Ticket();
            parkingRecords.put(ticket, car);
            return ticket;
        }
        throw new NoAvailablePositionExpection();
    }

    public Car fetch(Ticket ticket) {
        Car fetchedCar = parkingRecords.getOrDefault(ticket, null);
        if (fetchedCar == null)
            throw new UnrecognizedParkingTicketException();
        parkingRecords.remove(ticket);
        return fetchedCar;
    }

    public int getNumberOfEmptyPositions(){
        return MAX_CAPACITY - parkingRecords.size();
    }
}
