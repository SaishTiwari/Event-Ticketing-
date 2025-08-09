package com.example.event_ticketing_api;

import com.example.event_ticketing_api.entity.Event;
import com.example.event_ticketing_api.entity.Seat;
import com.example.event_ticketing_api.entity.SeatStatus;
import com.example.event_ticketing_api.repository.EventRepository;
import com.example.event_ticketing_api.repository.SeatRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.Instant;

@SpringBootApplication
public class EventTicketingApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventTicketingApiApplication.class, args);
	}

	@Bean
	CommandLineRunner initData(EventRepository eventRepo, SeatRepository seatRepo) {
		return args -> {
			if (eventRepo.count() == 0) {
				Event event = new Event();
				event.setName("Spring Boot Concert");
				event.setVenue("Big Hall");
				event.setStartTime(Instant.now().plusSeconds(3600));
				event = eventRepo.save(event);

				for (int row = 1; row <= 5; row++) {
					for (int seatNum = 1; seatNum <= 10; seatNum++) {
						Seat seat = new Seat();
						seat.setEvent(event);
						seat.setRow(String.valueOf((char) ('A' + row - 1)));
						seat.setSeatNumber(seatNum);
						seat.setSeatType("REGULAR");
						seat.setStatus(SeatStatus.FREE);
						seatRepo.save(seat);
					}
				}
				System.out.println("Sample event and seats created");
			}
		};
	}
}