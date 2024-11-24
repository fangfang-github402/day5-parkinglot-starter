package com.parkinglot;

import java.util.List;

public class ParkStrategyContext {
    private ParkStrategy parkStrategy;

    public ParkStrategyContext(ParkStrategy parkStrategy) {
        this.parkStrategy = parkStrategy;
    }

    public Ticket executeStrategy(List<ParkingLot> parkingLots, Car car) {
        return parkStrategy.park(parkingLots, car);
    }
}
