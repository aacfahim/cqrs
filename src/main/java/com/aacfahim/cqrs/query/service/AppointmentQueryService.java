package com.aacfahim.cqrs.query.service;

import com.aacfahim.cqrs.command.entity.Appointment;
import com.aacfahim.cqrs.query.dto.AppointmentRpDTO;
import com.aacfahim.cqrs.query.repository.AppointmentReadRepository;
import com.aacfahim.cqrs.query.service.impl.IAppointmentQueryService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AppointmentQueryService implements IAppointmentQueryService {
    private final AppointmentReadRepository appointmentReadRepository;

    public AppointmentQueryService(AppointmentReadRepository appointmentReadRepository) {
        this.appointmentReadRepository = appointmentReadRepository;
    }


    @Override
    public List<AppointmentRpDTO> getAll() {

        List<Appointment> appointments = appointmentReadRepository.findAll();

        return appointments.stream()
                .map(appointment -> AppointmentRpDTO.builder()
                        .patientId(appointment.getPatientId())
                        .doctorId(appointment.getDoctorId())
                        .appointmentTime(appointment.getAppointmentTime())
                        .status(appointment.getStatus())
                        .build()
                )
                .collect(Collectors.toList());
    }

    @Override
    public AppointmentRpDTO getAppointmentById(UUID id) {
        Appointment appointment = appointmentReadRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Appointment not found with ID: " + id));

        return AppointmentRpDTO.builder()
                .patientId(appointment.getPatientId())
                .doctorId(appointment.getDoctorId())
                .appointmentTime(appointment.getAppointmentTime())
                .status(appointment.getStatus())
                .build();
    }

    @Override
    public List<AppointmentRpDTO> getAppointmentsByDoctorOrPatient(UUID doctorId, UUID patientId) {
        List<Appointment> appointments;


        if (doctorId != null) {
            appointments = appointmentReadRepository.findByDoctorId(doctorId);
        } else if (patientId != null) {
            appointments = appointmentReadRepository.findByPatientId(patientId);
        } else {
            throw new IllegalArgumentException("Either doctor ID or patient ID must be provided.");
        }

        return appointments.stream()
                .map(appt -> AppointmentRpDTO.builder()
                        .patientId(appt.getPatientId())
                        .doctorId(appt.getDoctorId())
                        .appointmentTime(appt.getAppointmentTime())
                        .status(appt.getStatus())
                        .build()
                )
                .collect(Collectors.toList());
    }

    @Override
    public List<AppointmentRpDTO> getUpcomingAppointments() {
        List<Appointment> upcoming = appointmentReadRepository.findByAppointmentTimeAfter(LocalDateTime.now());

        return upcoming.stream()
                .map(appt -> AppointmentRpDTO.builder()
                        .patientId(appt.getPatientId())
                        .doctorId(appt.getDoctorId())
                        .appointmentTime(appt.getAppointmentTime())
                        .status(appt.getStatus())
                        .build()
                )
                .collect(Collectors.toList());
    }
}
