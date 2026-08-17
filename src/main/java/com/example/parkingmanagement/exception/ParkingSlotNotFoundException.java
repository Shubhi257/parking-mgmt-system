package com.example.parkingmanagement.exception;

public class ParkingSlotNotFoundException extends RuntimeException {

    public ParkingSlotNotFoundException(String message) {
        super(message);
    }
}