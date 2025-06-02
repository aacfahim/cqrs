package com.aacfahim.cqrs.command.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Appointment {
    @Id
    private UUID id;

    private UUID patientId;
    private UUID doctorId;
    private LocalDateTime appointmentTime;
    private String status; // BOOKED, CANCELLED
}
