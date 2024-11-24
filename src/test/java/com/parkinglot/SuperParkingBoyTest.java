package com.parkinglot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SuperParkingBoyTest {
    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setup() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    void should_in_firstParkingLot_when_park_given_two_parking_lots_and_with_same_number_of_empty_positions() {
        //Given
        SuperParkingBoy superParkingBoy = new SuperParkingBoy();
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        superParkingBoy.addParkingLot(firstParkingLot);
        superParkingBoy.addParkingLot(secondParkingLot);
        Car car = new Car(5);
        //When
        Ticket ticket = superParkingBoy.park(car);
        //Then
        assertNotNull(ticket);
        Assertions.assertThat(systemOut()).contains("The car:5 has parked in ParkingLot:1");
    }

    @Test
    void should_in_second_parking_lot_when_park_given_second_one_has_more_empty_positions() {
        //Given
        SuperParkingBoy superParkingBoy = new SuperParkingBoy();
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        superParkingBoy.addParkingLot(firstParkingLot);
        superParkingBoy.addParkingLot(secondParkingLot);
        superParkingBoy.park(new Car());
        Car car = new Car(6);
        //When
        Ticket ticket = superParkingBoy.park(car);
        //Then
        assertNotNull(ticket);
        Assertions.assertThat(systemOut()).contains("The car:6 has parked in ParkingLot:2");
    }

    @Test
    void should_return_right_car_when_fetch_given_a_parked_car_and_two_parking_tickets() {
        //Given
        SuperParkingBoy superParkingBoy = new SuperParkingBoy();
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        superParkingBoy.addParkingLot(firstParkingLot);
        superParkingBoy.addParkingLot(secondParkingLot);
        Car car = new Car();
        Ticket firstTicket = superParkingBoy.park(car);
        Car firstFetchedCar = superParkingBoy.fetch(firstTicket);
        superParkingBoy.park(new Car());
        Ticket secondTicket = superParkingBoy.park(car);
        Car secondFetchedCar = superParkingBoy.fetch(secondTicket);
        //When
        //Then
        assert (car.equals(firstFetchedCar));
        assert (car.equals(secondFetchedCar));
    }

    @Test
    void should_return_nothing_with_error_msg_when_fetch_given_two_parking_lot_and_an_unrecognized_ticket() {
        //Given
        SuperParkingBoy superParkingBoy = new SuperParkingBoy();
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        superParkingBoy.addParkingLot(firstParkingLot);
        superParkingBoy.addParkingLot(secondParkingLot);
        Car car = new Car();
        superParkingBoy.park(car);
        Ticket unrecognizedTicket = new Ticket();
        //When
        //Then
        assertThrows(UnrecognizedParkingTicketException.class, () -> superParkingBoy.fetch(unrecognizedTicket));
    }

    @Test
    void should_with_error_msg_when_fetch_given_two_parking_lot_and_a_used_ticket() {
        //Given
        SuperParkingBoy superParkingBoy = new SuperParkingBoy();
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        superParkingBoy.addParkingLot(firstParkingLot);
        superParkingBoy.addParkingLot(secondParkingLot);
        Car car = new Car();
        Ticket ticket = superParkingBoy.park(car);
        superParkingBoy.fetch(ticket);
        //When
        //Then
        assertThrows(UnrecognizedParkingTicketException.class, () -> superParkingBoy.fetch(ticket));
    }

    @Test
    void should_with_error_msg_when_park_given_two_parking_lot_and_without_any_position() {
        //Given
        SuperParkingBoy superParkingBoy = new SuperParkingBoy();
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        superParkingBoy.addParkingLot(firstParkingLot);
        superParkingBoy.addParkingLot(secondParkingLot);
        for (int i = 0; i < 20; i++) {
            superParkingBoy.park(new Car());
        }
        Car car = new Car();
        //When
        //Then
        assertThrows(NoAvailablePositionExpection.class, () -> superParkingBoy.park(car));
    }

    private String systemOut() {
        return outContent.toString();
    }

}
