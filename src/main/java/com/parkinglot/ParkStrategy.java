package com.parkinglot;

import java.util.List;

public interface ParkStrategy {
    Ticket park(List<ParkingLot> parkingLots, Car car);
}
