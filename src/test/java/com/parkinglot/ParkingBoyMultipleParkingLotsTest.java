package com.parkinglot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParkingBoyMultipleParkingLotsTest {
    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setup() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    void should_return_a_ticket_and_msg_with_firstParkingLot_when_park_given_both_with_available_position_and_a_car() {
        //Given
        ParkingBoy parkingBoy = new ParkingBoy();
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        parkingBoy.addParkingLot(firstParkingLot);
        parkingBoy.addParkingLot(secondParkingLot);
        Car car = new Car();
        //When
        Ticket ticket = parkingBoy.park(car);
        //Then
        assertNotNull(ticket);
        Assertions.assertThat(systemOut()).contains("The car has parked in ParkingLot:1");
    }

    @Test
    void should_return_a_ticket_and_msg_with_secondParkingLot_when_park_given_first_is_full_and_second_with_available_position() {
        //Given
        ParkingBoy parkingBoy = new ParkingBoy();
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        parkingBoy.addParkingLot(firstParkingLot);
        parkingBoy.addParkingLot(secondParkingLot);
        for (int i = 0; i < 10; i++) {
            parkingBoy.park(new Car());
        }
        Car car = new Car();
        //When
        Ticket ticket = parkingBoy.park(car);
        //Then
        assertNotNull(ticket);
        Assertions.assertThat(systemOut()).contains("The car has parked in ParkingLot:2");
    }

    @Test
    void should_return_the_right_car_with_each_ticket_when_fetch_twice_given_a_parked_car_and_two_parking_ticket() {
        //Given
        ParkingBoy parkingBoy = new ParkingBoy();
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        parkingBoy.addParkingLot(firstParkingLot);
        parkingBoy.addParkingLot(secondParkingLot);
        Car car = new Car();
        Ticket firstTicket = parkingBoy.park(car);
        for (int i = 0; i < 10; i++) {
            parkingBoy.park(new Car());
        }
        //When
        Car firstFetchedCar = parkingBoy.fetch(firstTicket);
        Ticket secondTicket = parkingBoy.park(car);
        Car secondFetchedCar = parkingBoy.fetch(secondTicket);
        //Then
        assert (car.equals(firstFetchedCar));
        assert (car.equals(secondFetchedCar));
    }

    @Test
    void should_return_nothing_with_error_msg_when_fetch_given_two_parking_lot_and_an_unrecognized_ticket() {
        //Given
        ParkingBoy parkingBoy = new ParkingBoy();
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        parkingBoy.addParkingLot(firstParkingLot);
        parkingBoy.addParkingLot(secondParkingLot);
        Car car = new Car();
        parkingBoy.park(car);
        Ticket unrecognizedTicket = new Ticket();
        //When
        //Then
        assertThrows(UnrecognizedParkingTicketException.class, () -> parkingBoy.fetch(unrecognizedTicket));
    }

    @Test
    void should_with_error_msg_when_fetch_given_two_parking_lot_and_a_used_ticket() {
        //Given
        ParkingBoy parkingBoy = new ParkingBoy();
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        parkingBoy.addParkingLot(firstParkingLot);
        parkingBoy.addParkingLot(secondParkingLot);
        Car car = new Car();
        Ticket ticket = parkingBoy.park(car);
        parkingBoy.fetch(ticket);
        //When
        //Then
        assertThrows(UnrecognizedParkingTicketException.class, () -> parkingBoy.fetch(ticket));
    }

    @Test
    void should_with_error_msg_when_park_given_two_parking_lot_and_without_any_position() {
        //Given
        ParkingBoy parkingBoy = new ParkingBoy();
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        parkingBoy.addParkingLot(firstParkingLot);
        parkingBoy.addParkingLot(secondParkingLot);
        for (int i = 0; i < 20; i++) {
            parkingBoy.park(new Car());
        }
        Car car = new Car();
        //When
        //Then
        assertThrows(NoAvailablePositionExpection.class, () -> parkingBoy.park(car));
    }

    private String systemOut() {
        return outContent.toString();
    }
}
