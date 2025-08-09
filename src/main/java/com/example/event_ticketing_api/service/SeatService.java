package com.example.event_ticketing_api.service;

import com.example.event_ticketing_api.entity.Seat;
import com.example.event_ticketing_api.entity.SeatStatus;
import com.example.event_ticketing_api.repository.SeatRepository;
import jakarta.persistence.OptimisticLockException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class SeatService {

    private final SeatRepository seatRepository;

    public SeatService(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    @Transactional
    public Seat lockSeat(String seatId) {
        Seat seat = seatRepository.findById(seatId)
                .orElseThrow(() -> new RuntimeException("Seat not found"));

        if (seat.getStatus() != SeatStatus.FREE) {
            throw new RuntimeException("Seat is not free and cannot be locked");
        }

        seat.setStatus(SeatStatus.LOCKED);

        try {
            return seatRepository.save(seat);
        } catch (OptimisticLockException e) {
            throw new RuntimeException("Failed to lock seat due to concurrent modification. Please try again.");
        }
    }

    @Transactional
    public Seat unlockSeat(String seatId) {
        Seat seat = seatRepository.findById(seatId)
                .orElseThrow(() -> new RuntimeException("Seat not found"));

        if (seat.getStatus() != SeatStatus.LOCKED) {
            throw new RuntimeException("Only locked seats can be unlocked");
        }

        seat.setStatus(SeatStatus.FREE);

        try {
            return seatRepository.save(seat);
        } catch (OptimisticLockException e) {
            throw new RuntimeException("Failed to unlock seat due to concurrent modification. Please try again.");
        }
    }

    @Transactional
    public Seat bookSeat(String seatId) {
        Seat seat = seatRepository.findById(seatId)
                .orElseThrow(() -> new RuntimeException("Seat not found"));

        if (seat.getStatus() != SeatStatus.LOCKED) {
            throw new RuntimeException("Only locked seats can be booked");
        }

        seat.setStatus(SeatStatus.BOOKED);

        try {
            return seatRepository.save(seat);
        } catch (OptimisticLockException e) {
            throw new RuntimeException("Failed to book seat due to concurrent modification. Please try again.");
        }
    }

    @Transactional(readOnly = true)
    public List<Seat> getSeatsByEvent(String eventId) {
        UUID uuid = UUID.fromString(eventId);
        return seatRepository.findByEventId(uuid);
    }

    @Transactional(readOnly = true)
    public Seat getSeatById(String seatId) {
        return seatRepository.findById(seatId)
                .orElseThrow(() -> new RuntimeException("Seat not found"));
    }

    @Transactional(readOnly = true)
    public List<Seat> getSeatsByStatus(com.example.event_ticketing_api.entity.SeatStatus status) {
        return seatRepository.findAll().stream()
                .filter(seat -> seat.getStatus() == status)
                .toList();
    }
}