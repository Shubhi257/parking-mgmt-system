package com.example.parkingmanagement.service;

import com.example.parkingmanagement.entity.ParkingSlot;
import com.example.parkingmanagement.entity.ParkingSlotStatus;
import com.example.parkingmanagement.exception.ParkingSlotAlreadyAvailableException;
import com.example.parkingmanagement.exception.ParkingSlotAlreadyOccupiedException;
import com.example.parkingmanagement.exception.ParkingSlotNotFoundException;
import com.example.parkingmanagement.repository.ParkingSlotRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingSlotService {

    private final ParkingSlotRepository parkingSlotRepository;

    public ParkingSlotService(ParkingSlotRepository parkingSlotRepository) {
        this.parkingSlotRepository = parkingSlotRepository;
    }

    public ParkingSlot addParkingSlot(ParkingSlot parkingSlot) {
        parkingSlot.setStatus(ParkingSlotStatus.AVAILABLE);
        return parkingSlotRepository.save(parkingSlot);
    }

    public List<ParkingSlot> getAllParkingSlots() {
        return parkingSlotRepository.findAll();
    }

    public ParkingSlot getParkingSlotById(Long id) {
        return parkingSlotRepository.findById(id)
                .orElseThrow(() -> new ParkingSlotNotFoundException(
                        "Parking slot not found with id:" + id
                ));
    }

    public ParkingSlot updateParkingSlot(Long id, ParkingSlot updatedSlot) {
        ParkingSlot existingSlot = parkingSlotRepository.findById(id)
                .orElseThrow(() ->
                        new ParkingSlotNotFoundException(
                                "Parking slot not found with id:" + id
                        ));

        existingSlot.setSlotNumber(updatedSlot.getSlotNumber());
        existingSlot.setStatus(updatedSlot.getStatus());

        return parkingSlotRepository.save(existingSlot);
    }

    public void deleteParkingSlot(Long id) {
        ParkingSlot existingSlot = parkingSlotRepository.findById(id)
                .orElseThrow(() ->
                        new ParkingSlotNotFoundException(
                                "Parking slot not found with id:" + id
                        ));

        parkingSlotRepository.delete(existingSlot);
    }

    public List<ParkingSlot> getAvailableParkingSlots() {
        return parkingSlotRepository.findByStatus(ParkingSlotStatus.AVAILABLE);
    }

    public ParkingSlot updateParkingSlotStatus(Long id, ParkingSlotStatus status) {

        ParkingSlot parkingSlot = parkingSlotRepository.findById(id)
                .orElseThrow(() ->
                        new ParkingSlotNotFoundException(
                                "Parking slot not found with id:" + id));

        parkingSlot.setStatus(status);

        return parkingSlotRepository.save(parkingSlot);
    }
    public ParkingSlot occupyParkingSlot(Long id) {

        ParkingSlot parkingSlot = parkingSlotRepository.findById(id)
                .orElseThrow(() ->
                        new ParkingSlotNotFoundException(
                                "Parking slot not found with id:" + id));

        if (parkingSlot.getStatus() == ParkingSlotStatus.OCCUPIED) {
            throw new ParkingSlotAlreadyOccupiedException(
                    "Parking slot is already occupied");        }

        parkingSlot.setStatus(ParkingSlotStatus.OCCUPIED);

        return parkingSlotRepository.save(parkingSlot);
    }

    public ParkingSlot releaseParkingSlot(Long id) {

        ParkingSlot parkingSlot = parkingSlotRepository.findById(id)
                .orElseThrow(() ->
                        new ParkingSlotNotFoundException(
                                "Parking slot not found with id: " + id));

        if (parkingSlot.getStatus() == ParkingSlotStatus.AVAILABLE) {
            throw new ParkingSlotAlreadyAvailableException(
                    "Parking slot is already available"
            );
        }

        parkingSlot.setStatus(ParkingSlotStatus.AVAILABLE);

        return parkingSlotRepository.save(parkingSlot);
    }
}