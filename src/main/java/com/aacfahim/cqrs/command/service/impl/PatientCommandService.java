package com.aacfahim.cqrs.command.service.impl;

import com.aacfahim.cqrs.command.dto.CreatePatientRqDTO;
import com.aacfahim.cqrs.command.entity.Patient;
import com.aacfahim.cqrs.command.repository.PatientRepository;
import com.aacfahim.cqrs.command.service.IPatientCommandService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PatientCommandService implements IPatientCommandService {

    private final PatientRepository patientRepository;

    public PatientCommandService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public UUID createPatient(CreatePatientRqDTO requestDto) {
        Patient patient = Patient.builder()
                .id(UUID.randomUUID())
                .name(requestDto.getName())
                .email(requestDto.getEmail())
                .phone(requestDto.getPhone())
                .build();

        patientRepository.save(patient);
        return patient.getId();
    }
}
