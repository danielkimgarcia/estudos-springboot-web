package com.danielkim.laboratory.springbootweb.api.domain.doctor;

import com.danielkim.laboratory.springbootweb.api.domain.address.Address;
import com.danielkim.laboratory.springbootweb.api.domain.enums.Speciality;

public record DoctorUpdatedData(Long id, String nome, String email, String crm, String phoneNumber, Speciality speciality, Address address) {

    public DoctorUpdatedData(Doctor doctor){
        this(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getCrm(), doctor.getPhoneNumber(), doctor.getSpeciality(), doctor.getAddress());
    }
}