package com.aacfahim.cqrs.command.service;

import com.aacfahim.cqrs.command.dto.CreatePatientRqDTO;

import java.util.UUID;

public interface IPatientCommandService {
    UUID createPatient(CreatePatientRqDTO createPatientRqDTO);
}
