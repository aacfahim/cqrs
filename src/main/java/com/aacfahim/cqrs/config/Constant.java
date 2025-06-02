package com.aacfahim.cqrs.config;

public final class Constant {

    private Constant() {}

    // === API Routes ===
    public static final String PATIENT_COMMAND_BASE_URL = "/api/command/patients";
    public static final String DOCTOR_COMMAND_BASE_URL = "/api/command/doctors";
    public static final String APPOINTMENT_COMMAND_BASE_URL = "/api/command/appointments";

    public static final String PATIENT_QUERY_BASE_URL = "/api/query/patients";
    public static final String DOCTOR_QUERY_BASE_URL = "/api/query/doctors";
    public static final String APPOINTMENT_QUERY_BASE_URL = "/api/query/appointments";

    // === Appointment Status ===
    public static final String STATUS_BOOKED = "BOOKED";
    public static final String STATUS_CANCELLED = "CANCELLED";

    // === Messages ===
    public static final String PATIENT_CREATED_SUCCESS = "Patient created successfully.";
    public static final String PATIENT_NOT_FOUND = "Patient not found.";
    public static final String DOCTOR_NOT_FOUND = "Doctor not found.";
    public static final String APPOINTMENT_NOT_FOUND = "Appointment not found.";
}
