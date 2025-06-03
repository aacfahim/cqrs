package com.aacfahim.cqrs.command.service;

import com.aacfahim.cqrs.command.dto.BookAppointmentRqDTO;

import java.util.UUID;

public interface IAppointmentCommandService {
    public UUID bookAppointment(BookAppointmentRqDTO bookAppointmentRqDTO);
    public void cancelAppointment(UUID appointmentId);
}
