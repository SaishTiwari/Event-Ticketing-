# Event Ticketing API with Seat Lock & Concurrency Control

A Spring Boot backend application designed to manage event ticket bookings with real-time seat locking and concurrency control. This project demonstrates handling high-concurrency scenarios using pessimistic locking to prevent double booking and ensures data consistency in a distributed environment.

---

## Features

- Create and manage events with seating layouts  
- Lock seats temporarily to prevent concurrent bookings  
- Pessimistic locking implementation to handle race conditions  
- Automatic cancellation of seat locks if payment is not completed within a specified time  
- Simulated payment API endpoint  
- JWT-based authentication and role-based access control  
- RESTful API design with Swagger/OpenAPI documentation  
- Unit and integration tests with JUnit and Mockito  

---

## Technologies Used

- Java 17+  
- Spring Boot  
- Spring Data JPA (Hibernate)  
- PostgreSQL  
- Spring Security with JWT  
- Swagger (Springfox or springdoc-openapi)  
- Maven/Gradle  
- JUnit 5 & Mockito  
- Docker (optional for containerization)  

---

## Getting Started

### Prerequisites

- Java JDK 17 or higher  
- PostgreSQL (v12+) installed and running  
- Maven or Gradle build tool  
- Git  
- Optional: Docker (for containerized setup)  

### Setup & Installation

1. **Clone the repository**  
```bash
git clone https://github.com/yourusername/event-ticketing-api.git
cd event-ticketing-api
