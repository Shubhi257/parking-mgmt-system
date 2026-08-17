package com.example.parkingmanagement.controller;

import com.example.parkingmanagement.entity.ParkingSlot;
import com.example.parkingmanagement.entity.ParkingSlotStatus;
import com.example.parkingmanagement.service.ParkingSlotService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parking-slots")
public class ParkingSlotController {

    private final ParkingSlotService parkingSlotService;

    public ParkingSlotController(ParkingSlotService parkingSlotService) {
        this.parkingSlotService = parkingSlotService;
    }

    @PostMapping
    public ParkingSlot addParkingSlot(@RequestBody ParkingSlot parkingSlot) {
        return parkingSlotService.addParkingSlot(parkingSlot);
    }

    @GetMapping
    public List<ParkingSlot> getAllParkingSlots() {
        return parkingSlotService.getAllParkingSlots();
    }

    @GetMapping("/{id}")
    public ParkingSlot getParkingSlotById(@PathVariable Long id) {
        return parkingSlotService.getParkingSlotById(id);
    }

    @PutMapping("/{id}")
    public ParkingSlot updateParkingSlot(
            @PathVariable Long id,
            @RequestBody ParkingSlot parkingSlot) {

        return parkingSlotService.updateParkingSlot(id, parkingSlot);
    }

    @DeleteMapping("/{id}")
    public void deleteParkingSlot(@PathVariable Long id) {
        parkingSlotService.deleteParkingSlot(id);
    }

    @GetMapping("/available")
    public List<ParkingSlot> getAvailableParkingSlots() {
        return parkingSlotService.getAvailableParkingSlots();
    }

    @PatchMapping("/{id}/status")
    public ParkingSlot updateParkingSlotStatus(
            @PathVariable Long id,
            @RequestParam ParkingSlotStatus status) {

        return parkingSlotService.updateParkingSlotStatus(id, status);
    }

    @PatchMapping("/{id}/occupy")
    public ParkingSlot occupyParkingSlot(@PathVariable Long id) {
        return parkingSlotService.occupyParkingSlot(id);
    }

    @PatchMapping("/{id}/release")
    public ParkingSlot releaseParkingSlot(@PathVariable Long id) {
        return parkingSlotService.releaseParkingSlot(id);
    }
}