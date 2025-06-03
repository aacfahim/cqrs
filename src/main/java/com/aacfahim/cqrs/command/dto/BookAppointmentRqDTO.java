package com.aacfahim.cqrs.command.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookAppointmentRqDTO {
    private UUID patientId;
    private UUID doctorId;
    private LocalDateTime appointmentTime;
}
