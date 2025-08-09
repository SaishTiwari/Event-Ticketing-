package com.example.event_ticketing_api.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "seats")
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    private String row;

    private int seatNumber;

    private String seatType; // e.g., REGULAR, VIP

    @Enumerated(EnumType.STRING)
    private SeatStatus status;

    @Version
    private Long version;  // For optimistic locking


    public Seat() {
    }

    public Seat(String id, Event event, String row, int seatNumber, String seatType, SeatStatus status, Long version) {
        this.id = id;
        this.event = event;
        this.row = row;
        this.seatNumber = seatNumber;
        this.seatType = seatType;
        this.status = status;
        this.version = version;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public String getRow() {
        return row;
    }

    public void setRow(String row) {
        this.row = row;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getSeatType() {
        return seatType;
    }

    public void setSeatType(String seatType) {
        this.seatType = seatType;
    }

    public SeatStatus getStatus() {
        return status;
    }

    public void setStatus(SeatStatus status) {
        this.status = status;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}