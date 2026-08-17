package com.example.parkingmanagement.repository;

import com.example.parkingmanagement.entity.ParkingSlot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingRepository extends JpaRepository<ParkingSlot, Long> {

}