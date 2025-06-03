package com.aacfahim.cqrs.query.controller;

import com.aacfahim.cqrs.query.dto.AppointmentRpDTO;
import com.aacfahim.cqrs.query.service.impl.IAppointmentQueryService;
import com.aacfahim.cqrs.utils.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static com.aacfahim.cqrs.config.Constant.APPOINTMENT_QUERY_BASE_URL;

@RestController
@RequestMapping(APPOINTMENT_QUERY_BASE_URL)
public class AppointmentQueryController {

    private final IAppointmentQueryService appointmentQueryService;

    public AppointmentQueryController(IAppointmentQueryService appointmentQueryService) {
        this.appointmentQueryService = appointmentQueryService;
    }

    // get all appointments
    @GetMapping
    public ResponseEntity<ApiResponse<List<AppointmentRpDTO>>> getAllAppointments() {
        List<AppointmentRpDTO> appointmentList = appointmentQueryService.getAll();

        ApiResponse<List<AppointmentRpDTO>> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                HttpStatus.OK.name(),
                "Fetched all appointments successfully.",
                appointmentList,
                null
        );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // get appointment by ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<AppointmentRpDTO>> getAppointmentById(@PathVariable UUID id) {
        AppointmentRpDTO dto = appointmentQueryService.getAppointmentById(id);
        ApiResponse<AppointmentRpDTO> response = new ApiResponse<>(
                HttpStatus.OK.value(), HttpStatus.OK.name(),
                "Fetched appointment details.", dto, null
        );
        return ResponseEntity.ok(response);
    }

    // llist all appointments by doctor or patient ID
    @GetMapping("/by")
    public ResponseEntity<ApiResponse<List<AppointmentRpDTO>>> getByDoctorOrPatient(
            @RequestParam(required = false) UUID doctorId,
            @RequestParam(required = false) UUID patientId) {

        List<AppointmentRpDTO> list = appointmentQueryService.getAppointmentsByDoctorOrPatient(doctorId, patientId);
        ApiResponse<List<AppointmentRpDTO>> response = new ApiResponse<>(
                HttpStatus.OK.value(), HttpStatus.OK.name(),
                "Fetched appointments by filter.", list, null
        );
        return ResponseEntity.ok(response);
    }

    // get all upcoming appointments
    @GetMapping("/upcoming")
    public ResponseEntity<ApiResponse<List<AppointmentRpDTO>>> getUpcomingAppointments() {
        List<AppointmentRpDTO> list = appointmentQueryService.getUpcomingAppointments();
        ApiResponse<List<AppointmentRpDTO>> response = new ApiResponse<>(
                HttpStatus.OK.value(), HttpStatus.OK.name(),
                "Fetched upcoming appointments.", list, null
        );
        return ResponseEntity.ok(response);
    }

}
