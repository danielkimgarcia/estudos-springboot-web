package com.danielkim.laboratory.springbootweb.api.domain.doctor;

import com.danielkim.laboratory.springbootweb.api.domain.enums.Speciality;

public record DoctorListData(Long id, String name, String email, String phoneNumber, String crm, Speciality speciality) {

    public DoctorListData(Doctor doctor) {
        this(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getPhoneNumber(), doctor.getCrm(), doctor.getSpeciality());
    }
}