package com.parkinglot;

public class NoAvailablePositionExpection extends RuntimeException {
    public static final String NO_AVAILABLE_POSITION = "No available position";

    public NoAvailablePositionExpection() {
        super(NO_AVAILABLE_POSITION);
    }
}
