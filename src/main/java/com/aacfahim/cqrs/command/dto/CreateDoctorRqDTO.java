package com.aacfahim.cqrs.command.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateDoctorRqDTO {
    private String name;
    private String specialization;
    private String email;
}
