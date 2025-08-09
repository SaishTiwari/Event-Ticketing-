package com.example.event_ticketing_api.controller;

import com.example.event_ticketing_api.entity.Seat;
import com.example.event_ticketing_api.entity.SeatStatus;
import com.example.event_ticketing_api.service.SeatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/seats")
public class SeatController {

    private final SeatService seatService;

    public SeatController(SeatService seatService) {
        this.seatService = seatService;
    }

    @PostMapping("/{seatId}/lock")
    public ResponseEntity<?> lockSeat(@PathVariable String seatId) {
        try {
            Seat lockedSeat = seatService.lockSeat(seatId);
            return ResponseEntity.ok(lockedSeat);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/{seatId}/unlock")
    public ResponseEntity<?> unlockSeat(@PathVariable String seatId) {
        try {
            Seat unlockedSeat = seatService.unlockSeat(seatId);
            return ResponseEntity.ok(unlockedSeat);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/{seatId}/book")
    public ResponseEntity<?> bookSeat(@PathVariable String seatId) {
        try {
            Seat bookedSeat = seatService.bookSeat(seatId);
            return ResponseEntity.ok(bookedSeat);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/event/{eventId}")
    public ResponseEntity<List<Seat>> getSeatsByEvent(@PathVariable String eventId) {
        List<Seat> seats = seatService.getSeatsByEvent(eventId);
        return ResponseEntity.ok(seats);
    }

    @GetMapping("/{seatId}")
    public ResponseEntity<?> getSeatById(@PathVariable String seatId) {
        try {
            Seat seat = seatService.getSeatById(seatId);
            return ResponseEntity.ok(seat);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Seat>> getSeatsByStatus(@PathVariable SeatStatus status) {
        List<Seat> seats = seatService.getSeatsByStatus(status);
        return ResponseEntity.ok(seats);
    }
}