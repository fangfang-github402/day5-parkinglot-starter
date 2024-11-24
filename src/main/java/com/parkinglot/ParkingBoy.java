package com.parkinglot;

import java.util.ArrayList;
import java.util.List;

public class ParkingBoy {
    protected List<ParkingLot> parkingLots;

    public ParkingBoy() {
        this.parkingLots = new ArrayList<>();
    }

    public Ticket park(Car car) {
        ParkStrategyContext parkStrategyContext = new ParkStrategyContext(new ParkingBoyStrategy());
        return parkStrategyContext.executeStrategy(parkingLots, car);
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
