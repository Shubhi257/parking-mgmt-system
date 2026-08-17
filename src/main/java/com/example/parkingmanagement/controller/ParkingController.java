package com.example.parkingmanagement.controller;

import com.example.parkingmanagement.service.ParkingService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ParkingController {
    private ParkingService parkingService;

    public ParkingController(ParkingService parkingService) {
        this.parkingService = parkingService;
    }
}