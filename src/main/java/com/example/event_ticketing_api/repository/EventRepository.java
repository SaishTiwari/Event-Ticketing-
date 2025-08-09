package com.example.event_ticketing_api.repository;

import com.example.event_ticketing_api.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EventRepository extends JpaRepository<Event, UUID> {
    // You can add custom query methods here later if needed
}