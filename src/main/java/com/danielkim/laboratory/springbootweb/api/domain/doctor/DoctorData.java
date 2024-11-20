package com.danielkim.laboratory.springbootweb.api.domain.doctor;

import com.danielkim.laboratory.springbootweb.api.domain.address.AddressData;
import com.danielkim.laboratory.springbootweb.api.domain.enums.Speciality;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DoctorData(
        @NotBlank(message = "{doctor.name.required}") String name,

        @NotBlank(message = "{doctor.secondName.required}") String secondName,
        @NotBlank(message = "{doctor.phoneNumber.required}") String phoneNumber,
        @NotBlank(message = "{doctor.email.required}") @Email(message = "{doctor.email.invalid}") String email,
        @NotBlank(message = "{doctor.crm.required}")
        @Pattern(message = "{doctor.crm.invalid}", regexp = "\\d{4,6}") String crm,
        @NotNull(message = "{doctor.speciality.required}")
        Speciality speciality,
        @NotNull(message = "{doctor.address.required}") @Valid
        AddressData address) {
}
