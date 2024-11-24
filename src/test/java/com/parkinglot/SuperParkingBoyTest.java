package com.parkinglot;

import org.assertj.core.api.Assertions;
import org.assertj.core.internal.bytebuddy.implementation.bind.annotation.Super;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertNotNull;

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
        Car car = new Car();
        //When
        Ticket ticket = superParkingBoy.park(car);
        //Then
        assertNotNull(ticket);
        Assertions.assertThat(systemOut()).contains("The car has parked in ParkingLot:1");
    }

    private String systemOut() {
        return outContent.toString();
    }

}
