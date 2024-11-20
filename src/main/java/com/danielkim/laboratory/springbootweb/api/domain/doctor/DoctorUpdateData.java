package com.danielkim.laboratory.springbootweb.api.domain.doctor;

import com.danielkim.laboratory.springbootweb.api.domain.address.AddressData;
import jakarta.validation.constraints.NotNull;

public record DoctorUpdateData(
        @NotNull(message = "{doctor.id.required}")
        Long id,
        String name,
        String phoneNumber,
        AddressData address) {
}