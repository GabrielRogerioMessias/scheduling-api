package com.messias.schedulingapi.controllers;

import com.messias.schedulingapi.domain.Branch;
import com.messias.schedulingapi.services.BranchService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/branchs")
public class BranchController {

    private final BranchService branchService;

    public BranchController(BranchService branchService) {
        this.branchService = branchService;
    }

    @GetMapping
    public ResponseEntity<List<Branch>> findAll() {
        List<Branch> branchList = branchService.findAll();
        return ResponseEntity.ok().body(branchList);
    }

    @DeleteMapping(value = "{idBranch}")
    public ResponseEntity<Void> delete(@PathVariable Integer idBranch) {
        branchService.delete(idBranch);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "{idBranch}")
    public ResponseEntity<Branch> findById(@PathVariable Integer idBranch) {
        Branch branchResult = branchService.findById(idBranch);
        return ResponseEntity.ok().body(branchResult);
    }

    @PostMapping
    public ResponseEntity<Branch> insert(@RequestBody Branch newBranch) {
        Branch branch = branchService.insert(newBranch);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(branch.getId()).toUri();
        return ResponseEntity.created(uri).body(branch);
    }

    @PutMapping("{idBranch}")
    public ResponseEntity<Branch> update(@PathVariable Integer idBranch, @RequestBody Branch updateBranch) {
        Branch branch = branchService.update(idBranch, updateBranch);
        return ResponseEntity.ok().body(branch);
    }
}
