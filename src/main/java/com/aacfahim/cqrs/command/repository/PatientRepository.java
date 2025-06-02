package com.aacfahim.cqrs.command.repository;

import com.aacfahim.cqrs.command.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PatientRepository extends JpaRepository<Patient, UUID> {

}
