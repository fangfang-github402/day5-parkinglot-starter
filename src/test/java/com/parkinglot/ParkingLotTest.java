package com.parkinglot;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ParkingLotTest {
    @Test
    void should_return_ticket_when_park_given_a_car() {
        //Given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        //When
        Ticket ticket = parkingLot.park(car);
        //Then
        assertNotNull(ticket);
    }

    @Test
    void should_return_the_parked_car_when_fetch_given_a_correct_ticket() {
        //Given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        Ticket ticket = parkingLot.park(car);
        //When
        Car fetchedCar = parkingLot.fetch(ticket);
        //Then
        assert (fetchedCar.equals(car));
    }

    @Test
    void should_return_right_car_with_car_ticket_when_fetch_the_given_two_cars() {
        //Given
        ParkingLot parkingLot = new ParkingLot();
        Car firstCar = new Car();
        Car secondCar = new Car();
        Ticket firstTicket = parkingLot.park(firstCar);
        Ticket secondTicket = parkingLot.park(secondCar);
        //When
        Car firstFetchedCar = parkingLot.fetch(firstTicket);
        Car secondFetchedCar = parkingLot.fetch(secondTicket);
        //Then
        assert (firstFetchedCar.equals(firstCar));
        assert (secondFetchedCar.equals(secondCar));
    }

    @Test
    void should_return_nothing_when_fetch_given_a_wrong_parking_ticket() {
        //Given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        parkingLot.park(car);
        Ticket wrongTicket = new Ticket();
        //When
        Car fetchedCar = parkingLot.fetch(wrongTicket);
        //Then
        assertNull(fetchedCar);
    }


    @Test
    void should_return_nothing_when_fetch_given_a_used_parking_ticket() {
        //Given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        Ticket ticket = parkingLot.park(car);
        parkingLot.fetch(ticket);
        //When
        Car fetchedCar = parkingLot.fetch(ticket);
        //Then
        assertNull(fetchedCar);
    }

    @Test
    void should_return_nothing_when_park_given_without_any_position() {
        //Given
        ParkingLot parkingLot = new ParkingLot();
        for (int i = 0; i < 10; i++) {
            parkingLot.park(new Car());
        }
        Car car = new Car();
        //When
        Ticket ticket = parkingLot.park(car);
        //Then
        assertNull(ticket);
    }

}
