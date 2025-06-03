package com.aacfahim.cqrs.performance;

import com.aacfahim.cqrs.command.dto.BookAppointmentRqDTO;
import com.aacfahim.cqrs.command.service.IAppointmentCommandService;
import com.aacfahim.cqrs.query.dto.AppointmentRpDTO;
import com.aacfahim.cqrs.query.service.impl.IAppointmentQueryService;
import com.aacfahim.cqrs.utils.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/performance")
public class PerformanceController {
    private final IAppointmentQueryService queryService;
    private final IAppointmentCommandService commandService;

    public PerformanceController(IAppointmentQueryService queryService, IAppointmentCommandService commandService) {
        this.queryService = queryService;
        this.commandService = commandService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Map<String, Object>>> compareCQRSReadPerformance() {
        Map<String, Object> result = new HashMap<>();

        // simulating CQRS read (query-side)
        long startCQRS = System.currentTimeMillis();
        List<AppointmentRpDTO> appointments = queryService.getAll(); // get all appointments
        long endCQRS = System.currentTimeMillis();

        result.put("cqrsReadTimeMillis", endCQRS - startCQRS);
        result.put("fetchedAppointmentsCount", appointments.size());
        result.put("timestamp", Instant.now());

        return ResponseEntity.ok(new ApiResponse<>(
                HttpStatus.OK.value(),
                HttpStatus.OK.name(),
                "Simulated CQRS performance read.",
                result,
                null
        ));
    }

    @PostMapping("/compare")
    public ResponseEntity<ApiResponse<Map<String, Object>>> compareReadWritePerformance(
            @RequestBody BookAppointmentRqDTO requestDto) {

        Map<String, Object> result = new HashMap<>();

        // 1. Measure write time: create appointment with payload data
        long startWrite = System.currentTimeMillis();

        UUID appointmentId = commandService.bookAppointment(requestDto);

        long endWrite = System.currentTimeMillis();
        long writeDuration = endWrite - startWrite;

        // 2. Measure read time: get all appointments
        long startRead = System.currentTimeMillis();

//        List<AppointmentRpDTO> allAppointments = queryService.getAll();
        AppointmentRpDTO allAppointments = queryService.getAll().getFirst();

        long endRead = System.currentTimeMillis();
        long readDuration = endRead - startRead;

        // 3. Prepare result
        result.put("writeTimeMillis", writeDuration);
        result.put("readTimeMillis", readDuration);
        result.put("createdAppointmentId", appointmentId);
//        result.put("totalAppointments", allAppointments.size());
        result.put("first appointment fetched ", allAppointments);
        result.put("timestamp", Instant.now());

        return ResponseEntity.ok(new ApiResponse<>(
                HttpStatus.OK.value(),
                HttpStatus.OK.name(),
                "Performance comparison of write vs read operations.",
                result,
                null
        ));
    }
}
