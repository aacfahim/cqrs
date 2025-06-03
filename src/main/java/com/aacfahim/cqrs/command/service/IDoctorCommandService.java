package com.aacfahim.cqrs.command.service;

import com.aacfahim.cqrs.command.dto.CreateDoctorRqDTO;

import java.util.UUID;

public interface IDoctorCommandService {
    UUID createDoctor(CreateDoctorRqDTO request);
}
