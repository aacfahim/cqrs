package com.aacfahim.cqrs.query.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppointmentRpDTO {

    private UUID patientId;
    private UUID doctorId;
    private LocalDateTime appointmentTime;
    private String status; // BOOKED, CANCELLED
}
