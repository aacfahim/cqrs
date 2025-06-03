package com.aacfahim.cqrs.command.controller;

import com.aacfahim.cqrs.command.dto.CreateDoctorRqDTO;
import com.aacfahim.cqrs.command.service.IDoctorCommandService;
import com.aacfahim.cqrs.utils.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static com.aacfahim.cqrs.config.Constant.DOCTOR_COMMAND_BASE_URL;
import static com.aacfahim.cqrs.config.Constant.DOCTOR_CREATED_SUCCESS;

@RestController
@RequestMapping(DOCTOR_COMMAND_BASE_URL)
public class DoctorCommandController {
    private final IDoctorCommandService doctorCommandService;


    public DoctorCommandController(IDoctorCommandService doctorCommandService) {
        this.doctorCommandService = doctorCommandService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<UUID>> createDoctor(@RequestBody CreateDoctorRqDTO doctorRqDTO){
        UUID id = doctorCommandService.createDoctor(doctorRqDTO);

        ApiResponse<UUID> response = new ApiResponse<>(
                HttpStatus.CREATED.value(),
                HttpStatus.CREATED.name(),
                DOCTOR_CREATED_SUCCESS,
                 id,
                null
        );

        return new ResponseEntity<>(response, HttpStatus.CREATED);


    }
}
