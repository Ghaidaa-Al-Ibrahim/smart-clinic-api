# SmartClinic API 🏥

A RESTful API for managing clinic operations including 
appointment booking, medical records, and multi-role 
authentication.

## Tech Stack

- Java 17
- Spring Boot 3
- Spring Security + JWT
- MySQL
- Docker
- JUnit 5

## Features

- Multi-role authentication (Admin, Doctor, Patient)
- Appointment booking with conflict detection
- Medical records management
- Doctor schedule management
- Admin dashboard with reports

## Getting Started

### Prerequisites
- Java 17
- MySQL 8
- Maven

### Setup

1. Clone the repository
git clone https://github.com/[username]/smart-clinic-api

2. Create database
CREATE DATABASE smart_clinic;

3. Configure application
cp src/main/resources/application-example.properties \
   src/main/resources/application.properties
# Edit application.properties with your DB credentials

4. Run the application
mvn spring-boot:run

## API Endpoints

### Auth
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | /api/auth/register | Register new patient |
| POST | /api/auth/login | Login for all roles |

### Doctor
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | /api/doctor/appointments | Get today's appointments |
| POST | /api/doctor/appointments/{id}/medical-record | Write diagnosis |

### Patient
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | /api/patients/doctors | Browse doctors |
| POST | /api/appointments | Book appointment |
| PUT | /api/appointments/{id}/cancel | Cancel appointment |

## Database Schema

![ERD Diagram](docs/erd.png)
