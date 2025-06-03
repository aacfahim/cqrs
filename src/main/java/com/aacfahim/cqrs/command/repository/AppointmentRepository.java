package com.aacfahim.cqrs.command.repository;

import com.aacfahim.cqrs.command.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AppointmentRepository extends JpaRepository<Appointment, UUID> {
}
