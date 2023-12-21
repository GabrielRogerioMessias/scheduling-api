package com.messias.schedulingapi.controllers;

import com.messias.schedulingapi.domain.Scheduling;
import com.messias.schedulingapi.services.SchedulingService;
import com.messias.schedulingapi.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/schedulings")
public class SchedulingController {

    private final SchedulingService schedulingService;

    public SchedulingController(SchedulingService schedulingService) {
        this.schedulingService = schedulingService;
    }

    @GetMapping
    public ResponseEntity<List<Scheduling>> findAll() {
        List<Scheduling> schedulingList = schedulingService.findAll();
        return ResponseEntity.ok().body(schedulingList);
    }

    @DeleteMapping(value = "/{idScheduling}")
    public ResponseEntity<Void> delete(@PathVariable Integer idScheduling) {
        schedulingService.delete(idScheduling);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{idScheduling}")
    public ResponseEntity<Scheduling> findById(@PathVariable Integer idScheduling) {
        Scheduling scheduling = schedulingService.findById(idScheduling);
        return ResponseEntity.ok().body(scheduling);
    }

    @PostMapping
    public ResponseEntity<Scheduling> insert(@RequestBody Scheduling newScheduling) {
        Scheduling scheduling = schedulingService.insert(newScheduling);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/id").buildAndExpand(newScheduling.getId()).toUri();
        return ResponseEntity.created(uri).body(scheduling);
    }

    @PutMapping(value = "/{idScheduling}")
    public ResponseEntity<Scheduling> update(@PathVariable Integer idScheduling, @RequestBody Scheduling updateScheduling) {
        schedulingService.update(idScheduling, updateScheduling);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


}
