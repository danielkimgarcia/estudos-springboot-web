package com.danielkim.laboratory.springbootweb.api.domain.patient;

import com.danielkim.laboratory.springbootweb.api.domain.address.AddressData;
import jakarta.validation.constraints.NotNull;

public record PatientUpdateData(
        @NotNull
        Long id,
        String name,
        String phoneNumber,
        AddressData address) {
}