package com.danielkim.laboratory.springbootweb.api.domain.patient;

import com.danielkim.laboratory.springbootweb.api.domain.address.Address;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "patients")
@Entity(name = "Patient")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;

    private String phoneNumber;

    private String cpf;

    @Embedded
    private Address address;

    private Boolean active;

    public Patient(PatientData data) {
        this.active = true;
        this.name = data.name();
        this.email = data.email();
        this.phoneNumber = data.phoneNumber();
        this.cpf = data.cpf();
        this.address = new Address(data.address());
    }

    public void updateRegistryInformation(PatientUpdateData data) {
        if (data.name() != null) {
            this.name = data.name();
        }
        if (data.phoneNumber() != null) {
            this.phoneNumber = data.phoneNumber();
        }
        if (data.address() != null) {
            this.address.updateRegistryInformation(data.address());
        }
    }

    public void delete() {
        this.active = false;
    }
}
