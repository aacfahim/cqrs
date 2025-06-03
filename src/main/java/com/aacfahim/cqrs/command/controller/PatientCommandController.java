package com.aacfahim.cqrs.command.controller;

import com.aacfahim.cqrs.command.dto.CreatePatientRqDTO;
import com.aacfahim.cqrs.command.service.IPatientCommandService;
import com.aacfahim.cqrs.utils.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static com.aacfahim.cqrs.config.Constant.PATIENT_COMMAND_BASE_URL;
import static com.aacfahim.cqrs.config.Constant.PATIENT_CREATED_SUCCESS;

@RestController
@RequestMapping(PATIENT_COMMAND_BASE_URL)
public class PatientCommandController {

    private final IPatientCommandService patientCommandService;

    public PatientCommandController(IPatientCommandService patientCommandService) {
        this.patientCommandService = patientCommandService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<UUID>> createPatient(@RequestBody CreatePatientRqDTO requestDto) {
        UUID patientId = patientCommandService.createPatient(requestDto);
         ApiResponse<UUID> response = new ApiResponse<>(
                HttpStatus.CREATED.value(),
                HttpStatus.CREATED.name(),
                PATIENT_CREATED_SUCCESS,
                 patientId,
                null
        );

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
