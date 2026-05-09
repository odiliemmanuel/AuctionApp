# Auction Platform

A microservices-based auction system built with Spring Boot, Apache Kafka, MongoDB, and Docker Compose.

This project consists of two services:

-   AuctionSystem → Main auction backend (manages auctions, bids, users)
-   Emailer → Notification service that listens to Kafka events and sends email notifications


## Architecture

User Request
     |
     v
AuctionSystem (Spring Boot)
     |
 Publishes Events
     |
     v
Kafka Broker
     |
 Consumes Events
     |
     v
Emailer Service
     |
 Sends Email Notifications




## Tech Stack

- Java 17
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Apache Kafka
- Docker
- Docker Compose
- Maven



## Project Structure


auction-platform/
│
├── AuctionSystem/
│   ├── src/
│   ├── Dockerfile
│   └── pom.xml
│
├── emailer/
│   ├── src/
│   ├── Dockerfile
│   └── pom.xml
│
└── docker-compose.yml




## Requirements

Before running the project, install:

- Java 17+
- Maven
- Docker
- Docker Compose


## How to Run

### 1. Clone the repository

```
git clone https://github.com/odiliemmanuel/AuctionApp.git
cd auction-platform
```

---

### 2. Build both services

Build the JAR files for both microservices:

#### Build AuctionSystem

```
cd AuctionSystem
mvn clean package
```

#### Buil Emailer

```
cd ../emailer
mvn clean package
```

---

### 3. Start everything with Docker

Go back to the root folder:

```
cd ..
docker compose up --build
```

This starts:

- PostgreSQL
- Zookeeper
- Kafka
- AuctionSystem
- Emailer

---

## Available Services

| Service | URL | Description |
|---------|-----|-------------|
| Auction API | http://localhost:8080 | Main backend API |
| Emailer Service | http://localhost:8093 | Email notification service |
| PostgreSQL | localhost:5432 | Database |
| Kafka | localhost:9092 | Event broker |

---

## Event Flow

### Example: User places a bid

1. User sends a bid request to **AuctionSystem**
2. AuctionSystem saves the bid to PostgreSQL
3. AuctionSystem publishes a Kafka event (`NEW_BID`)
4. Emailer consumes the event
5. Emailer sends a notification email

---

## Environment Variables

Configuration is handled through Docker Compose.

Example:

```
SPRING_DATASOURCE_URL=jdbc:postgresql://postgres:5432/auctiondb
SPRING_DATASOURCE_USERNAME=postgres
SPRING_DATASOURCE_PASSWORD=password
SPRING_KAFKA_BOOTSTRAP_SERVERS=kafka:9092
```

---

## Common Commands

### Stop containers

```
docker compose down
```

### Restart containers

```
docker compose up
```

### Rebuild after code changes

```
docker compose up --build
```

---

## Troubleshooting

### Port already in use

Check if another service is using:

- 8080
- 8093
- 5432
- 9092

---

### Kafka connection issues

Make sure Docker uses:

```
KAFKA_ADVERTISED_LISTENERS: PLAINTEXT://kafka:9092
```

**Do not use `localhost:9092` inside Docker containers.**

---

## Future Improvements

- Authentication & Authorization
- Swagger/OpenAPI documentation
- pgAdmin integration
- Kafka UI dashboard
- CI/CD pipeline
- Kubernetes deployment

---

## Author

Built by **Kaodilichi Emmanuel Ejeh**# AuctionApp
