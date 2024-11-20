package com.danielkim.laboratory.springbootweb.api.domain.doctor;

import com.danielkim.laboratory.springbootweb.api.domain.enums.Speciality;
import com.danielkim.laboratory.springbootweb.api.domain.address.Address;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "doctor")
@Entity(name = "Doctor")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    private String secondName;
    private String email;

    private String phoneNumber;
    private String crm;

    @Enumerated(EnumType.STRING)
    private Speciality speciality;
    @Embedded
    private Address address;

    private boolean active;

    public Doctor(DoctorData doctorData) {
        this.active = true;
        this.name = doctorData.name();
        this.secondName = doctorData.secondName();
        this.email = doctorData.email();
        this.phoneNumber = doctorData.phoneNumber();
        this.crm = doctorData.crm();
        this.speciality = doctorData.speciality();
        this.address = new Address(doctorData.address());
    }

    public void updateRegistryInformation(DoctorUpdateData doctorDataUpdate){
        if(doctorDataUpdate.name() != null){
            this.name = doctorDataUpdate.name();
        }
        if(doctorDataUpdate.phoneNumber() != null){
            this.phoneNumber = doctorDataUpdate.phoneNumber();
        }
        if(doctorDataUpdate.address() != null){
            this.address.updateRegistryInformation(doctorDataUpdate.address());
        }
    }

    public void delete(){
        this.active = false;
    }
}