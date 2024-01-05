package com.messias.schedulingapi.controllers;

import com.messias.schedulingapi.domain.Permission;
import com.messias.schedulingapi.services.PermissionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/permissions")
public class PermissionController {
    private final PermissionService permissionService;

    public PermissionController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @DeleteMapping(value = "/{idPermission}")
    public ResponseEntity<Void> delete(@PathVariable Integer idPermission) {
        permissionService.delete(idPermission);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Permission>> findAll() {
        List<Permission> permissionList = permissionService.findAll();
        return ResponseEntity.ok().body(permissionList);
    }

    @GetMapping(value = "/{idPermission}")
    public ResponseEntity<Permission> findById(@PathVariable Integer idPermission) {
        Permission permission = permissionService.findById(idPermission);
        return ResponseEntity.ok().body(permission);
    }

    @PostMapping
    public ResponseEntity<Permission> insert(@RequestBody Permission newPermission) {
        Permission permission = permissionService.insert(newPermission);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/id").buildAndExpand(newPermission.getId()).toUri();
        return ResponseEntity.created(uri).body(permission);
    }

    @PutMapping(value = "/{idPermission}")
    public ResponseEntity<Permission> update(@PathVariable Integer idPermission, @RequestBody Permission updatePermission) {
        permissionService.update(idPermission, updatePermission);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}