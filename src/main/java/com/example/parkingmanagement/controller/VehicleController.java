package com.example.parkingmanagement.controller;

import com.example.parkingmanagement.dto.VehicleRequest;
import com.example.parkingmanagement.dto.VehicleResponse;
import com.example.parkingmanagement.entity.Vehicle;
import com.example.parkingmanagement.service.VehicleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping("/vehicles")
    public Vehicle addVehicle(@RequestBody VehicleRequest vehicleRequest) {
        return vehicleService.addVehicle(vehicleRequest);
    }

    @GetMapping("/vehicles")
    public List<Vehicle> getAllVehicles() {
        return vehicleService.getAllVehicles();
    }

    @GetMapping("/vehicles/{id}")
    public VehicleResponse getVehicleById(@PathVariable Long id) {
        return vehicleService.getVehicleByID(id);
    }

    @DeleteMapping("/vehicles/{id}")
    public void deleteVehicle(@PathVariable Long id) {
        vehicleService.deleteVehicle(id);
    }

    @PutMapping("/vehicles/{id}")
    public Vehicle updateVehicle(
            @PathVariable Long id,
            @RequestBody Vehicle vehicle) {

        return vehicleService.updateVehicle(id, vehicle);
    }
}