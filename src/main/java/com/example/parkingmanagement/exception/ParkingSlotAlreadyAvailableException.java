package com.example.parkingmanagement.exception;

public class ParkingSlotAlreadyAvailableException extends RuntimeException {

    public ParkingSlotAlreadyAvailableException(String message) {
        super(message);
    }
}