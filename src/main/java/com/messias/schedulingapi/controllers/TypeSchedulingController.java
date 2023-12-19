package com.messias.schedulingapi.controllers;

import com.messias.schedulingapi.domain.Employer;
import com.messias.schedulingapi.domain.TypeScheduling;
import com.messias.schedulingapi.services.SchedulingService;
import com.messias.schedulingapi.services.TypeSchedulingService;
import com.messias.schedulingapi.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/type-schedulings")
public class TypeSchedulingController {

    private TypeSchedulingService typeSchedulingService;

    public TypeSchedulingController(TypeSchedulingService typeSchedulingService) {
        this.typeSchedulingService = typeSchedulingService;
    }


    @DeleteMapping(value = "/{idTypeScheduling}")
    public ResponseEntity<Void> delete(@PathVariable Integer idTypeScheduling) {
        typeSchedulingService.delete(idTypeScheduling);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<TypeScheduling>> findAll() {
        List<TypeScheduling> typeSchedulingList = typeSchedulingService.findAll();
        return ResponseEntity.ok().body(typeSchedulingList);
    }

    @GetMapping(value = "/{idTypeScheduling}")
    public ResponseEntity<TypeScheduling> findById(@PathVariable Integer idTypeScheduling) {
        TypeScheduling typeScheduling = typeSchedulingService.findById(idTypeScheduling);
        return ResponseEntity.ok().body(typeScheduling);
    }

    @PostMapping
    public ResponseEntity<TypeScheduling> insert(@RequestBody TypeScheduling typeScheduling) {
        TypeScheduling newTypeScheduling = typeSchedulingService.insert(typeScheduling);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/id").buildAndExpand(newTypeScheduling.getIdTypeScheduling()).toUri();
        return ResponseEntity.created(uri).body(newTypeScheduling);
    }

    @PutMapping(value = "/{idTypeScheduling}")
    public ResponseEntity<TypeScheduling> update(@PathVariable Integer idTypeScheduling, @RequestBody TypeScheduling updateTypeScheduling) {
        typeSchedulingService.update(idTypeScheduling, updateTypeScheduling);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
