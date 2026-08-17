package com.example.parkingmanagement.repository;

import com.example.parkingmanagement.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface  VehicleRepository extends JpaRepository<Vehicle, Long> {


}