package com.messias.schedulingapi.controllers;

import com.messias.schedulingapi.domain.Doctor;
import com.messias.schedulingapi.services.BranchService;
import com.messias.schedulingapi.services.DoctorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/doctors")
public class DoctorController {

    private DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @DeleteMapping(value = "/{idDoctor}")
    public ResponseEntity<Void> delete(@PathVariable Integer idDoctor) {
        doctorService.delete(idDoctor);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Doctor>> findAll() {
        List<Doctor> listDoctors = doctorService.findAll();
        return ResponseEntity.ok().body(listDoctors);
    }

    @GetMapping(value = "/{idDoctor}")
    public ResponseEntity<Doctor> findById(@PathVariable Integer idDoctor) {
        Doctor doctor = doctorService.findById(idDoctor);
        return ResponseEntity.ok().body(doctor);
    }

    @PostMapping
    public ResponseEntity<Doctor> insert(@RequestBody Doctor doctor) {
        Doctor newDoctor = doctorService.insert(doctor);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/id").buildAndExpand(newDoctor.getId()).toUri();
        return ResponseEntity.created(uri).body(newDoctor);
    }

    @PutMapping(value = "/{idDoctor}")
    public ResponseEntity<Doctor> update(@PathVariable Integer idDoctor, @RequestBody Doctor updateDoctor) {
        doctorService.update(idDoctor, updateDoctor);
        return ResponseEntity.ok().body(updateDoctor);
    }

}
