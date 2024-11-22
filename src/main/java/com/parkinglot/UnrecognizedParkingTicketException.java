package com.parkinglot;

public class UnrecognizedParkingTicketException extends RuntimeException{
    public UnrecognizedParkingTicketException(String msg){
        super(msg);
    }
}
