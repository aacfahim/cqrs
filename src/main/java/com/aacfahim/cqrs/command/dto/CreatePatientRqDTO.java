package com.aacfahim.cqrs.command.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreatePatientRqDTO {
    private String name;
    private String email;
    private String phone;
}
