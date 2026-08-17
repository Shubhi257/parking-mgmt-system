package com.example.parkingmanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ParkingSlotAlreadyOccupiedException.class)
    public ResponseEntity<Map<String, String>> handleParkingSlotAlreadyOccupied(
            ParkingSlotAlreadyOccupiedException exception) {

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(Map.of("message", exception.getMessage()));
    }
    @ExceptionHandler(ParkingSlotAlreadyAvailableException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleParkingSlotAlreadyAvailableException(
            ParkingSlotAlreadyAvailableException ex) {

        return ex.getMessage();
    }
}