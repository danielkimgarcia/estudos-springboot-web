package com.danielkim.laboratory.springbootweb.api.domain.patient;

import com.danielkim.laboratory.springbootweb.api.domain.address.AddressData;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record PatientData(
        @NotBlank
        String name,
        @NotBlank
        @Email
        String email,

        @NotBlank
        String phoneNumber,
        @NotBlank
        @Pattern(regexp = "\\d{3}\\d{3}\\d{3}\\d{2}")
        String cpf,

        @NotNull @Valid AddressData address) {
}