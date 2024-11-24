package com.parkinglot;

import java.util.Comparator;

public class SmartParkingBoy extends ParkingBoy {

    @Override
    public Ticket park(Car car) {
        ParkStrategyContext parkStrategyContext = new ParkStrategyContext(new SmartParkingBoyStrategy());
        return parkStrategyContext.executeStrategy(parkingLots, car);
    }
}
