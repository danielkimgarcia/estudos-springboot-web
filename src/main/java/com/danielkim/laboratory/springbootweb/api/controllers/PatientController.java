package com.danielkim.laboratory.springbootweb.api.controllers;

import com.danielkim.laboratory.springbootweb.api.domain.patient.*;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private PatientRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<PatientUpdatedData> savePatient(@RequestBody @Valid PatientData patientData, UriComponentsBuilder uriBuilder) {
        var patient = new Patient(patientData);

        repository.save(patient);

        var uri = uriBuilder.path("/doctor/{id}").buildAndExpand(patient.getId()).toUri();

        return ResponseEntity.created(uri).body(new PatientUpdatedData(patient));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<PatientUpdatedData> updatePatient(@RequestBody @Valid PatientUpdateData patientData) {
        var patient = repository.getReferenceById(patientData.id());

        patient.updateRegistryInformation(patientData);

        return ResponseEntity.ok(new PatientUpdatedData(patient));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deletePatient(@PathVariable("id") Long id) {
        var patient = repository.getReferenceById(id);

        patient.delete();

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<PatientListData>> getAllPatients(@PageableDefault(size = 10, sort = {"name"}) Pageable pagination) {
        var page = repository.findAllByActiveTrue(pagination).map(PatientListData::new);

        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientUpdatedData> patientDetail(@PathVariable Long id) {
        var patient = repository.getReferenceById(id);

        return ResponseEntity.ok(new PatientUpdatedData(patient));
    }
}