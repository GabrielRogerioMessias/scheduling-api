package com.messias.schedulingapi.controllers;

import com.messias.schedulingapi.domain.Branch;
import com.messias.schedulingapi.services.BranchService;
import com.messias.schedulingapi.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/branchs")
public class BranchController {

    private BranchService branchService;

    public BranchController(BranchService branchService) {
        this.branchService = branchService;
    }

    @GetMapping
    public ResponseEntity<List<Branch>> listAllBranches() {
        List<Branch> branchList = branchService.findAll();
        return ResponseEntity.ok().body(branchList);
    }

    @PostMapping
    public ResponseEntity<Branch> insert(@RequestBody Branch newBranch) {
        Branch branch = branchService.insert(newBranch);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(branch.getId()).toUri();
        return ResponseEntity.created(uri).body(branch);
    }
}
