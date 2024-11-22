package com.parkinglot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ParkingBoyMultipleParkingLotsTest {
    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setup() {
        System.setOut(new PrintStream(outContent));
    }
    @Test
    void should_return_a_ticket_and_msg_with_firstParkingLot_when_park_given_both_with_available_position_and_a_car(){
        //Given
        ParkingBoy parkingBoy =new ParkingBoy();
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        parkingBoy.addParkingLot(firstParkingLot);
        parkingBoy.addParkingLot(secondParkingLot);
        Car car =new Car();
        //When
        Ticket ticket = parkingBoy.park(car);
        //Then
        assertNotNull(ticket);
        Assertions.assertThat(systemOut()).contains("The car has parked in ParkingLot:01");
     }

    private String systemOut() {
        return outContent.toString();
    }
}
