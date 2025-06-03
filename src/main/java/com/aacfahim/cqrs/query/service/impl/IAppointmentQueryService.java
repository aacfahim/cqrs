package com.aacfahim.cqrs.query.service.impl;

import com.aacfahim.cqrs.query.dto.AppointmentRpDTO;

import java.util.List;
import java.util.UUID;

public interface IAppointmentQueryService {

    List<AppointmentRpDTO> getAll();
    AppointmentRpDTO getAppointmentById(UUID id);
    List<AppointmentRpDTO> getAppointmentsByDoctorOrPatient(UUID doctorId, UUID patientId);
    public List<AppointmentRpDTO> getUpcomingAppointments();

}