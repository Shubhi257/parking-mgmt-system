package com.example.parkingmanagement.service;

import com.example.parkingmanagement.dto.VehicleRequest;
import com.example.parkingmanagement.dto.VehicleResponse;
import com.example.parkingmanagement.entity.Vehicle;
import com.example.parkingmanagement.exception.VehicleNotFoundException;
import com.example.parkingmanagement.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public Vehicle addVehicle(VehicleRequest vehicleRequest) {

        Vehicle vehicle = new Vehicle();

        vehicle.setVehicleNumber(vehicleRequest.getVehicleNumber());
        vehicle.setOwnerName(vehicleRequest.getOwnerName());
        vehicle.setVehicleType(vehicleRequest.getVehicleType());

        return vehicleRepository.save(vehicle);
    }

    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    public VehicleResponse getVehicleByID(Long id) {

        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() ->
                        new VehicleNotFoundException(
                                "Vehicle not found with id: " + id
                        )
                );

        return new VehicleResponse(
                vehicle.getId(),
                vehicle.getVehicleNumber(),
                vehicle.getOwnerName(),
                vehicle.getVehicleType()
        );
    }

    public void deleteVehicle(Long id) {

        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() ->
                        new VehicleNotFoundException(
                                "Vehicle not found with id: " + id
                        )
                );

        vehicleRepository.delete(vehicle);
    }

    public Vehicle updateVehicle(Long id, Vehicle vehicleDetails) {

        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() ->
                        new VehicleNotFoundException(
                                "Vehicle not found with id: " + id
                        )
                );

        vehicle.setVehicleNumber(vehicleDetails.getVehicleNumber());
        vehicle.setOwnerName(vehicleDetails.getOwnerName());
        vehicle.setVehicleType(vehicleDetails.getVehicleType());

        return vehicleRepository.save(vehicle);
    }
}