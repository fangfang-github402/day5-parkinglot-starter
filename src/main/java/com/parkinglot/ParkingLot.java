package com.parkinglot;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    public static final int MAX_CAPACITY = 10;
    public static final String NO_AVAILABLE_POSITION = "No available position";
    public static final String UNRECOGNIZED_PARKING_TICKET = "Unrecognized parking ticket";
    private Map<Ticket, Car> parkingRecords = new HashMap<>();

    public Ticket park(Car car) {
        if (parkingRecords.size() < MAX_CAPACITY) {
            Ticket ticket = new Ticket();
            parkingRecords.put(ticket, car);
            return ticket;
        }
        throw new RuntimeException(NO_AVAILABLE_POSITION);
    }

    public Car fetch(Ticket ticket) {
        Car fetchedCar = parkingRecords.getOrDefault(ticket, null);
        if (fetchedCar == null)
            throw new RuntimeException(UNRECOGNIZED_PARKING_TICKET);
        parkingRecords.remove(ticket);
        return fetchedCar;
    }
}
