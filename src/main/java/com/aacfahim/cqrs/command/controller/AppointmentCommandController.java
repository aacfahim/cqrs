package com.aacfahim.cqrs.command.controller;

import com.aacfahim.cqrs.command.dto.BookAppointmentRqDTO;
import com.aacfahim.cqrs.command.service.IAppointmentCommandService;
import com.aacfahim.cqrs.utils.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.aacfahim.cqrs.config.Constant.*;

@RestController
@RequestMapping(APPOINTMENT_COMMAND_BASE_URL)
public class AppointmentCommandController {

    private final IAppointmentCommandService appointmentCommandService;

    public AppointmentCommandController(IAppointmentCommandService appointmentCommandService) {
        this.appointmentCommandService = appointmentCommandService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<UUID>> bookAppointment(@RequestBody BookAppointmentRqDTO requestDto){
        UUID id = appointmentCommandService.bookAppointment(requestDto);

        ApiResponse<UUID> response = new ApiResponse<>(
                HttpStatus.CREATED.value(),
                HttpStatus.CREATED.name(),
                APPOINTMENT_CREATED_SUCCESS,
                id,
                null
        );

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @PostMapping("/{appointmentId}/cancel")
    public ResponseEntity<ApiResponse<Void>> cancelAppointment(@PathVariable UUID appointmentId) {
        appointmentCommandService.cancelAppointment(appointmentId);
        ApiResponse<Void> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                HttpStatus.OK.name(),
                APPOINTMENT_CANCELLED_SUCCESS,
                null,
        null

        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
