package com.messias.schedulingapi.controllers;

import com.messias.schedulingapi.domain.Doctor;
import com.messias.schedulingapi.domain.Employer;
import com.messias.schedulingapi.services.DoctorService;
import com.messias.schedulingapi.services.EmployerService;
import com.messias.schedulingapi.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/employers")
public class EmployerController {

    private final EmployerService employerService;

    public EmployerController(EmployerService employerService) {
        this.employerService = employerService;
    }


    @DeleteMapping(value = "/{idEmployer}")
    public ResponseEntity<Void> delete(@PathVariable Integer idEmployer) {
        employerService.delete(idEmployer);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Employer>> findAll() {
        List<Employer> employerList = employerService.findAll();
        return ResponseEntity.ok().body(employerList);
    }

    @GetMapping(value = "/{idEmployer}")
    public ResponseEntity<Employer> findById(@PathVariable Integer idEmployer) {
        Employer employer = employerService.findById(idEmployer);
        return ResponseEntity.ok().body(employer);
    }

    @PostMapping
    public ResponseEntity<Employer> insert(@RequestBody Employer employer) {
        Employer newEmployer = employerService.insert(employer);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/id").buildAndExpand(employer.getId()).toUri();
        return ResponseEntity.created(uri).body(newEmployer);
    }

    @PutMapping(value = "/{idEmployer}")
    public ResponseEntity<Employer> update(@PathVariable Integer idEmployer, @RequestBody Employer updateEmployer) {
        employerService.update(idEmployer, updateEmployer);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


}
