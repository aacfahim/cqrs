package com.aacfahim.cqrs.command.service.impl;

import com.aacfahim.cqrs.command.dto.CreateDoctorRqDTO;
import com.aacfahim.cqrs.command.entity.Doctor;
import com.aacfahim.cqrs.command.repository.DoctorRepository;
import com.aacfahim.cqrs.command.service.IDoctorCommandService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DoctorCommandService implements IDoctorCommandService {

    private final DoctorRepository doctorRepository;

    public DoctorCommandService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }


    @Override
    public UUID createDoctor(CreateDoctorRqDTO requestDto) {

        Doctor doctor = Doctor.builder()
                .name(requestDto.getName())
                .specialization(requestDto.getSpecialization())
                .email(requestDto.getEmail())
        .build();
        return doctorRepository.save(doctor).getId();
    }
}
