package com.danielkim.laboratory.springbootweb.api.domain.address;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private String streetName;
    private String neighborhood;
    private String cep;
    private String number;
    private String complement;
    private String city;
    private String uf;

    public Address(AddressData address) {
        this.streetName = address.streetName();
        this.neighborhood = address.neighborhood();
        this.cep = address.cep();
        this.uf = address.uf();
        this.city = address.city();
        this.number = address.number();
        this.complement = address.complement();
    }

    public void updateRegistryInformation(AddressData address) {

        if(address.streetName() != null) {
            this.streetName = address.streetName();
        }
        if(address.neighborhood() != null) {
            this.neighborhood = address.neighborhood();
        }
        if(address.cep() != null) {
            this.cep = address.cep();
        }
        if(address.uf() != null) {
            this.uf = address.uf();
        }
        if(address.city() != null) {
            this.city = address.city();
        }
        if(address.number() != null) {
            this.number = address.number();
        }
        if(address.complement() != null) {
            this.complement = address.complement();
        }
    }
}