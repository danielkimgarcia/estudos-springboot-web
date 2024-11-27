package com.danielkim.laboratory.springbootweb.api.domain.patient;

import com.danielkim.laboratory.springbootweb.api.domain.address.Address;

public record PatientUpdatedData(Long id, String name, String email, String cpf, String phoneNumber, Address address) {

    public PatientUpdatedData(Patient patient) {
        this(patient.getId(), patient.getName(), patient.getEmail(), patient.getCpf(), patient.getPhoneNumber(), patient.getAddress());
    }
}