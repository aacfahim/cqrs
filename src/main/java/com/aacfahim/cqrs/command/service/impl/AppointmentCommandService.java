package com.aacfahim.cqrs.command.service.impl;

import com.aacfahim.cqrs.command.dto.BookAppointmentRqDTO;
import com.aacfahim.cqrs.command.entity.Appointment;
import com.aacfahim.cqrs.command.repository.AppointmentRepository;
import com.aacfahim.cqrs.command.service.IAppointmentCommandService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AppointmentCommandService implements IAppointmentCommandService {

    private final AppointmentRepository appointmentRepository;

    public AppointmentCommandService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }


    @Override
    public UUID bookAppointment(BookAppointmentRqDTO requestDto) {
        Appointment appointment = Appointment.builder()
                .patientId(requestDto.getPatientId())
                .doctorId(requestDto.getDoctorId())
                .appointmentTime(requestDto.getAppointmentTime())
                .status("BOOKED")
                .build();

        Appointment saved = appointmentRepository.save(appointment);
        return saved.getId();
    }

    @Override
    public void cancelAppointment(UUID appointmentId) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new EntityNotFoundException("Appointment not found with ID: " + appointmentId));

        appointment.setStatus("CANCELLED");
        appointmentRepository.save(appointment);
    }
}
