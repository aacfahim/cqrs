package com.aacfahim.cqrs.command.repository;

import com.aacfahim.cqrs.command.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DoctorRepository extends JpaRepository<Doctor, UUID> {
}
