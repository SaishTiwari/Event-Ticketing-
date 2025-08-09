package com.example.event_ticketing_api.repository;

import com.example.event_ticketing_api.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SeatRepository extends JpaRepository<Seat, String> {

    // Use UUID type for eventId
    List<Seat> findByEventId(UUID eventId);
}