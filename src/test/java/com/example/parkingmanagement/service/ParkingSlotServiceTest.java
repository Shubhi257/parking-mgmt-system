package com.example.parkingmanagement.service;

import com.example.parkingmanagement.entity.ParkingSlot;
import com.example.parkingmanagement.entity.ParkingSlotStatus;
import com.example.parkingmanagement.exception.ParkingSlotAlreadyAvailableException;
import com.example.parkingmanagement.exception.ParkingSlotAlreadyOccupiedException;
import com.example.parkingmanagement.exception.ParkingSlotNotFoundException;
import com.example.parkingmanagement.repository.ParkingSlotRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ParkingSlotServiceTest {

    @Mock
    private ParkingSlotRepository parkingSlotRepository;

    @InjectMocks
    private ParkingSlotService parkingSlotService;

    @Test
    void occupyParkingSlot_shouldChangeStatusToOccupied() {

        ParkingSlot parkingSlot = new ParkingSlot();

        parkingSlot.setSlotNumber("A-01");
        parkingSlot.setStatus(ParkingSlotStatus.AVAILABLE);

        when(parkingSlotRepository.findById(1L))
                .thenReturn(java.util.Optional.of(parkingSlot));

        when(parkingSlotRepository.save(parkingSlot))
                .thenReturn(parkingSlot);

        ParkingSlot result = parkingSlotService.occupyParkingSlot(1L);

        assertEquals(ParkingSlotStatus.OCCUPIED, result.getStatus());
    }

    @Test
    void occupyParkingSlot_shouldThrowExceptionWhenSlotIsAlreadyOccupied() {

        ParkingSlot parkingSlot = new ParkingSlot();

        parkingSlot.setStatus(ParkingSlotStatus.OCCUPIED);

        when(parkingSlotRepository.findById(2L))
                .thenReturn(java.util.Optional.of(parkingSlot));

        assertThrows(
                ParkingSlotAlreadyOccupiedException.class,
                () -> parkingSlotService.occupyParkingSlot(2L)
        );
    }

    @Test
    void releaseParkingSlot_shouldChangeStatusToAvailable() {

        ParkingSlot parkingSlot = new ParkingSlot();

        parkingSlot.setStatus(ParkingSlotStatus.OCCUPIED);

        when(parkingSlotRepository.findById(3L))
                .thenReturn(java.util.Optional.of(parkingSlot));

        when(parkingSlotRepository.save(parkingSlot))
                .thenReturn(parkingSlot);

        ParkingSlot result = parkingSlotService.releaseParkingSlot(3L);

        assertEquals(ParkingSlotStatus.AVAILABLE, result.getStatus());
    }

    @Test
    void releaseParkingSlot_shouldThrowExceptionWhenSlotIsAlreadyAvailable() {

        ParkingSlot parkingSlot = new ParkingSlot();

        parkingSlot.setStatus(ParkingSlotStatus.AVAILABLE);

        when(parkingSlotRepository.findById(3L))
                .thenReturn(java.util.Optional.of(parkingSlot));

        assertThrows(
                ParkingSlotAlreadyAvailableException.class,
                () -> parkingSlotService.releaseParkingSlot(3L)
        );
    }

    @Test
    void getParkingSlotById_shouldReturnParkingSlotWhenItExists() {

        ParkingSlot parkingSlot = new ParkingSlot();

        parkingSlot.setStatus(ParkingSlotStatus.AVAILABLE);

        when(parkingSlotRepository.findById(5L))
                .thenReturn(java.util.Optional.of(parkingSlot));

        ParkingSlot result = parkingSlotService.getParkingSlotById(5L);

        assertEquals(parkingSlot, result);
    }

    @Test
    void getParkingSlotById_shouldThrowExceptionWhenSlotDoesNotExist() {

        when(parkingSlotRepository.findById(999L))
                .thenReturn(java.util.Optional.empty());

        assertThrows(
                ParkingSlotNotFoundException.class,
                () -> parkingSlotService.getParkingSlotById(999L)
        );
    }

    @Test
    void addParkingSlot_shouldSetStatusToAvailable() {

        ParkingSlot parkingSlot = new ParkingSlot();

        parkingSlot.setStatus(null);

        when(parkingSlotRepository.save(parkingSlot))
                .thenReturn(parkingSlot);

        ParkingSlot result = parkingSlotService.addParkingSlot(parkingSlot);

        assertEquals(ParkingSlotStatus.AVAILABLE, result.getStatus());
    }

    @Test
    void getAllParkingSlots_shouldReturnAllParkingSlots() {

        ParkingSlot slot1 = new ParkingSlot();
        slot1.setStatus(ParkingSlotStatus.AVAILABLE);

        ParkingSlot slot2 = new ParkingSlot();
        slot2.setStatus(ParkingSlotStatus.OCCUPIED);

        List<ParkingSlot> parkingSlots = List.of(slot1, slot2);

        when(parkingSlotRepository.findAll())
                .thenReturn(parkingSlots);

        List<ParkingSlot> result = parkingSlotService.getAllParkingSlots();

        assertEquals(2, result.size());
        assertEquals(parkingSlots, result);
    }

    @Test
    void getAvailableParkingSlots_shouldReturnAvailableSlots() {

        ParkingSlot slot1 = new ParkingSlot();
        slot1.setStatus(ParkingSlotStatus.AVAILABLE);

        ParkingSlot slot2 = new ParkingSlot();
        slot2.setStatus(ParkingSlotStatus.AVAILABLE);

        List<ParkingSlot> availableSlots = List.of(slot1, slot2);

        when(parkingSlotRepository.findByStatus(ParkingSlotStatus.AVAILABLE))
                .thenReturn(availableSlots);

        List<ParkingSlot> result =
                parkingSlotService.getAvailableParkingSlots();

        assertEquals(2, result.size());
        assertEquals(availableSlots, result);
    }

    @Test
    void updateParkingSlot_shouldUpdateSlotNumberAndStatus() {

        ParkingSlot existingSlot = new ParkingSlot();
        existingSlot.setSlotNumber("A-01");
        existingSlot.setStatus(ParkingSlotStatus.AVAILABLE);

        ParkingSlot updatedSlot = new ParkingSlot();
        updatedSlot.setSlotNumber("A-10");
        updatedSlot.setStatus(ParkingSlotStatus.OCCUPIED);

        when(parkingSlotRepository.findById(4L))
                .thenReturn(java.util.Optional.of(existingSlot));

        when(parkingSlotRepository.save(existingSlot))
                .thenReturn(existingSlot);

        ParkingSlot result =
                parkingSlotService.updateParkingSlot(4L, updatedSlot);

        assertEquals("A-10", result.getSlotNumber());
        assertEquals(ParkingSlotStatus.OCCUPIED, result.getStatus());
    }

    @Test
    void updateParkingSlot_shouldThrowExceptionWhenSlotDoesNotExist() {

        ParkingSlot updatedSlot = new ParkingSlot();
        updatedSlot.setSlotNumber("A-10");
        updatedSlot.setStatus(ParkingSlotStatus.OCCUPIED);

        when(parkingSlotRepository.findById(999L))
                .thenReturn(java.util.Optional.empty());

        assertThrows(
                ParkingSlotNotFoundException.class,
                () -> parkingSlotService.updateParkingSlot(999L, updatedSlot)
        );
    }

    @Test
    void deleteParkingSlot_shouldDeleteSlotWhenItExists() {

        ParkingSlot parkingSlot = new ParkingSlot();
        parkingSlot.setStatus(ParkingSlotStatus.AVAILABLE);

        when(parkingSlotRepository.findById(5L))
                .thenReturn(java.util.Optional.of(parkingSlot));

        parkingSlotService.deleteParkingSlot(5L);

        org.mockito.Mockito.verify(parkingSlotRepository)
                .delete(parkingSlot);
    }

    @Test
    void deleteParkingSlot_shouldThrowExceptionWhenSlotDoesNotExist() {

        when(parkingSlotRepository.findById(999L))
                .thenReturn(java.util.Optional.empty());

        assertThrows(
                ParkingSlotNotFoundException.class,
                () -> parkingSlotService.deleteParkingSlot(999L)
        );
    }

    @Test
    void updateParkingSlotStatus_shouldUpdateStatus() {

        ParkingSlot parkingSlot = new ParkingSlot();
        parkingSlot.setStatus(ParkingSlotStatus.AVAILABLE);

        when(parkingSlotRepository.findById(6L))
                .thenReturn(java.util.Optional.of(parkingSlot));

        when(parkingSlotRepository.save(parkingSlot))
                .thenReturn(parkingSlot);

        ParkingSlot result =
                parkingSlotService.updateParkingSlotStatus(
                        6L,
                        ParkingSlotStatus.OCCUPIED
                );

        assertEquals(ParkingSlotStatus.OCCUPIED, result.getStatus());
    }

    @Test
    void updateParkingSlotStatus_shouldThrowExceptionWhenSlotDoesNotExist() {

        when(parkingSlotRepository.findById(999L))
                .thenReturn(java.util.Optional.empty());

        assertThrows(
                ParkingSlotNotFoundException.class,
                () -> parkingSlotService.updateParkingSlotStatus(
                        999L,
                        ParkingSlotStatus.OCCUPIED
                )
        );
    }
}