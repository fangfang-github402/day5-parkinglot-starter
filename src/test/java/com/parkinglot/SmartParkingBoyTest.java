package com.parkinglot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SmartParkingBoyTest {
    private ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    public void setup() {
        System.setOut(new PrintStream(outContent));
    }
    @Test
    void should_in_firstParkingLot_when_park_given_two_parking_lots_and_with_same_number_of_empty_positions(){
        //Given
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy();
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        smartParkingBoy.addParkingLot(firstParkingLot);
        smartParkingBoy.addParkingLot(secondParkingLot);
        Car car = new Car();
        //When
        Ticket ticket = smartParkingBoy.park(car);
        //Then
        assertNotNull(ticket);
        Assertions.assertThat(systemOut()).contains("The car has parked in ParkingLot:1");
     }
    
     @Test
     void should_in_second_parking_lot_when_park_given_second_one_has_more_empty_positions(){
         //Given
         SmartParkingBoy smartParkingBoy = new SmartParkingBoy();
         ParkingLot firstParkingLot = new ParkingLot();
         ParkingLot secondParkingLot = new ParkingLot();
         smartParkingBoy.addParkingLot(firstParkingLot);
         smartParkingBoy.addParkingLot(secondParkingLot);
         smartParkingBoy.park(new Car());
         Car car = new Car();
         //When
         Ticket ticket = smartParkingBoy.park(car);
         //Then
         assertNotNull(ticket);
         Assertions.assertThat(systemOut()).contains("The car has parked in ParkingLot:2");
      }

      @Test
      void should_return_right_car_when_fetch_given_a_parked_car_and_two_parking_tickets(){
          //Given
          SmartParkingBoy smartParkingBoy = new SmartParkingBoy();
          ParkingLot firstParkingLot = new ParkingLot();
          ParkingLot secondParkingLot = new ParkingLot();
          smartParkingBoy.addParkingLot(firstParkingLot);
          smartParkingBoy.addParkingLot(secondParkingLot);
          Car car = new Car();
          Ticket firstTicket = smartParkingBoy.park(car);
          Car firstFetchedCar = smartParkingBoy.fetch(firstTicket);
          smartParkingBoy.park(new Car());
          Ticket secondTicket = smartParkingBoy.park(car);
          Car secondFetchedCar = smartParkingBoy.fetch(secondTicket);
          //When
          //Then
          assert (car.equals(firstFetchedCar));
          assert (car.equals(secondFetchedCar));
       }

       @Test
       void should_with_error_msg_when_fetch_given_an_unrecognized_ticket(){
           //Given
           SmartParkingBoy smartParkingBoy = new SmartParkingBoy();
           ParkingLot firstParkingLot = new ParkingLot();
           ParkingLot secondParkingLot = new ParkingLot();
           smartParkingBoy.addParkingLot(firstParkingLot);
           smartParkingBoy.addParkingLot(secondParkingLot);
           Car car = new Car();
           smartParkingBoy.park(car);
           Ticket unrecognizedTicket = new Ticket();
           //When
           //Then
           assertThrows(UnrecognizedParkingTicketException.class, () -> smartParkingBoy.fetch(unrecognizedTicket));
       }

       @Test
       void should_with_error_msg_when_fetch_given_a_used_ticket(){
           //Given
           SmartParkingBoy smartParkingBoy = new SmartParkingBoy();
           ParkingLot firstParkingLot = new ParkingLot();
           ParkingLot secondParkingLot = new ParkingLot();
           smartParkingBoy.addParkingLot(firstParkingLot);
           smartParkingBoy.addParkingLot(secondParkingLot);
           Car car = new Car();
           Ticket ticket = smartParkingBoy.park(car);
           smartParkingBoy.fetch(ticket);
           //When
           //Then
           assertThrows(UnrecognizedParkingTicketException.class, () -> smartParkingBoy.fetch(ticket));
       }

    @Test
    void should_with_error_msg_when_park_given_two_parking_lot_and_without_any_position() {
        //Given
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy();
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        smartParkingBoy.addParkingLot(firstParkingLot);
        smartParkingBoy.addParkingLot(secondParkingLot);
        for (int i = 0; i < 20; i++) {
            smartParkingBoy.park(new Car());
        }
        Car car = new Car();
        //When
        //Then
        assertThrows(NoAvailablePositionExpection.class, () -> smartParkingBoy.park(car));
    }

    private String systemOut() {
        return outContent.toString();
    }
}
