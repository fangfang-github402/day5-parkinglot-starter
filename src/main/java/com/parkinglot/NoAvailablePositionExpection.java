package com.parkinglot;

public class NoAvailablePositionExpection extends RuntimeException{
    public NoAvailablePositionExpection(String msg){
        super(msg);
    }
}
