package com.messias.schedulingapi.controllers;

import com.messias.schedulingapi.domain.SchedulingEmployer;
import com.messias.schedulingapi.services.SchedulingEmployerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/scheduling-employers")
public class SchedulingEmployerController {

    private SchedulingEmployerService schedulingEmployerService;

    public SchedulingEmployerController(SchedulingEmployerService schedulingEmployerService) {
        this.schedulingEmployerService = schedulingEmployerService;
    }

    @DeleteMapping(value = "/{idSchedulingEmployer}")
    public ResponseEntity<Void> delete(@PathVariable Integer idSchedulingEmployer) {
        schedulingEmployerService.delete(idSchedulingEmployer);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<SchedulingEmployer>> findAll() {
        List<SchedulingEmployer> schedulingEmployerList = schedulingEmployerService.findAll();
        return ResponseEntity.ok().body(schedulingEmployerList);
    }

    @GetMapping(value = "/{idSchedulingEmployer}")
    public ResponseEntity<SchedulingEmployer> findById(@PathVariable Integer idSchedulingEmployer) {
        SchedulingEmployer schedulingEmployer = schedulingEmployerService.findById(idSchedulingEmployer);
        return ResponseEntity.ok().body(schedulingEmployer);
    }

    @PostMapping
    public ResponseEntity<SchedulingEmployer> insert(@RequestBody SchedulingEmployer newSchedulingEmployer) {
        schedulingEmployerService.insert(newSchedulingEmployer);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/id").buildAndExpand(newSchedulingEmployer.getId()).toUri();
        return ResponseEntity.created(uri).body(newSchedulingEmployer);
    }


    @PutMapping(value = "/{idOldSchedulingEmployer}")
    public ResponseEntity<SchedulingEmployer> update(@PathVariable Integer idOldSchedulingEmployer,
                                                     @RequestBody SchedulingEmployer updateSchedulingEmployer) {
        SchedulingEmployer schedulingEmployer = schedulingEmployerService.update(idOldSchedulingEmployer, updateSchedulingEmployer);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
