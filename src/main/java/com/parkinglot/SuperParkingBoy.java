package com.parkinglot;

import java.util.Comparator;

public class SuperParkingBoy extends ParkingBoy {

    @Override
    public Ticket park(Car car) {
        ParkStrategyContext parkStrategyContext = new ParkStrategyContext(new SuperParkingBoyStrategy());
        return parkStrategyContext.executeStrategy(parkingLots, car);
    }

}
