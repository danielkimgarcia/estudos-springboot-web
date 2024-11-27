package com.danielkim.laboratory.springbootweb.api.controllers;

import com.danielkim.laboratory.springbootweb.api.domain.doctor.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/doctor")
public class DoctorController {
    @Autowired
    private DoctorRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<DoctorUpdatedData> saveDoctor(@RequestBody @Valid DoctorData doctorData, UriComponentsBuilder uriBuilder) {
        var doctor = new Doctor(doctorData);

        repository.save(doctor);

        var uri = uriBuilder.path("/doctor/{id}").buildAndExpand(doctor.getId()).toUri();

        return ResponseEntity.created(uri).body(new DoctorUpdatedData(doctor));
    }

    @GetMapping
    public ResponseEntity<Page<DoctorListData>> getAllDoctors(@PageableDefault(size = 10, sort = {"name"}) Pageable pagination) {

        var page = repository.findAllByActiveTrue(pagination).map(DoctorListData::new);

        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DoctorUpdatedData> updateDoctor(@RequestBody @Valid DoctorUpdateData doctorData) {
        var doctor = repository.getReferenceById(doctorData.id());

        doctor.updateRegistryInformation(doctorData);

        return ResponseEntity.ok(new DoctorUpdatedData(doctor));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        var doctor = repository.getReferenceById(id);

        doctor.delete();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorUpdatedData> doctorDetail(@PathVariable Long id) {
        var doctor = repository.getReferenceById(id);

        return ResponseEntity.ok(new DoctorUpdatedData(doctor));
    }
}