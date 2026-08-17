package com.example.parkingmanagement.service;

import com.example.parkingmanagement.repository.ParkingRepository;
import org.springframework.stereotype.Service;



@Service
public class ParkingService {
    private ParkingRepository parkingRepository;

    public ParkingService(ParkingRepository parkingRepository) {
        this.parkingRepository = parkingRepository;
    }
}