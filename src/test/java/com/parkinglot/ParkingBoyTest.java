package com.parkinglot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParkingBoyTest {
    @Test
    void should_return_a_parking_ticket_when_park_given_a_car_and_a_parking_lot() {
        //Given
        ParkingBoy parkingBoy = new ParkingBoy();
        Car car = new Car();
        //When
        Ticket ticket = parkingBoy.park(car);
        //Then
        assertNotNull(ticket);
    }

    @Test
    void should_return_the_parked_car_when_park_given_a_parking_ticket() {
        //Given
        ParkingBoy parkingBoy = new ParkingBoy();
        Car car = new Car();
        Ticket ticket = parkingBoy.park(car);
        //When
        Car fetchedCar = parkingBoy.fetch(ticket);
        //Then
        assert (car.equals(fetchedCar));
    }

    @Test
    void should_return_right_car_with_car_ticket_when_fetch_the_given_two_cars() {
        //Given
        ParkingBoy parkingBoy = new ParkingBoy();
        Car firstCar = new Car();
        Car secondCar = new Car();
        Ticket firstTicket = parkingBoy.park(firstCar);
        Ticket secondTicket = parkingBoy.park(secondCar);
        //When
        Car firstFetchedCar = parkingBoy.fetch(firstTicket);
        Car secondFetchedCar = parkingBoy.fetch(secondTicket);
        //Then
        assert (firstFetchedCar.equals(firstCar));
        assert (secondFetchedCar.equals(secondCar));
    }

    @Test
    void should_with_error_msg_when_fetch_given_an_unrecognized_ticket() {
        //Given
        ParkingBoy parkingBoy = new ParkingBoy();
        Car car = new Car();
        parkingBoy.park(car);
        Ticket unrecognizedTicket = new Ticket();
        //When
        //Then
        assertThrows(UnrecognizedParkingTicketException.class, () -> parkingBoy.fetch(unrecognizedTicket));
    }


}
