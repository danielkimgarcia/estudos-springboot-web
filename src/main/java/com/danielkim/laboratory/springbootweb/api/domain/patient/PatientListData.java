package com.danielkim.laboratory.springbootweb.api.domain.patient;

public record PatientListData(Long id, String nome, String email, String cpf) {

    public PatientListData(Patient paciente) {
        this(paciente.getId(), paciente.getName(), paciente.getEmail(), paciente.getCpf());
    }
}