package com.example.parkingmanagement.repository;

import com.example.parkingmanagement.entity.ParkingSlot;
import com.example.parkingmanagement.entity.ParkingSlotStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParkingSlotRepository extends JpaRepository<ParkingSlot, Long> {
    List<ParkingSlot> findByStatus(ParkingSlotStatus status);

}