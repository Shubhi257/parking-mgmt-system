package com.example.parkingmanagement.exception;


public class ParkingSlotAlreadyOccupiedException extends RuntimeException {

    public ParkingSlotAlreadyOccupiedException(String message) {
        super(message);
    }
}