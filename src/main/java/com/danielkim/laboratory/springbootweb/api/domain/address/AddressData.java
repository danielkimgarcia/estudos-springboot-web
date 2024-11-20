package com.danielkim.laboratory.springbootweb.api.domain.address;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record AddressData(
        @NotBlank(message = "{address.streetName.required}")
        String streetName,
        @NotBlank(message = "{address.neighborhood.required}")
        String neighborhood,
        @NotBlank(message = "{address.cep.required}")
        @Pattern(message = "{address.cep.invalid}", regexp = "\\d{8}")
        String cep,
        @NotBlank(message = "{address.city.required}")
        String city,
        @NotBlank(message = "{address.uf.required}")
        String uf,
        String complement,
        String number) {
}