package com.messias.schedulingapi.services;

import com.messias.schedulingapi.domain.Permission;
import com.messias.schedulingapi.repositories.PermissionRepository;
import com.messias.schedulingapi.services.exceptionsServices.DatabaseException;
import com.messias.schedulingapi.services.exceptionsServices.ResourceNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class PermissionService {
    private final PermissionRepository permissionRepository;

    public PermissionService(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    public List<Permission> findAll() {
        return permissionRepository.findAll();
    }

    public Permission findByDescription(String description) {
        Permission permission = permissionRepository.findPermissionByDescription(description).orElseThrow(() -> new ResourceNotFoundException(Permission.class, description));
        return permission;
    }

    public Permission findById(Integer idPermission) {
        Permission permission = permissionRepository.findById(idPermission).orElseThrow(() -> new ResourceNotFoundException(Permission.class, idPermission));
        return permission;

    }

    public void delete(Integer idPermission) {
        try {
            Permission permission = permissionRepository.findById(idPermission).orElseThrow(() -> new ResourceNotFoundException(Permission.class, idPermission));
            permissionRepository.delete(permission);

        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public Permission insert(Permission permission) {
        return permissionRepository.save(permission);
    }

    public Permission update(Integer idPermission, Permission updatePermission) {
        Permission oldPermission = permissionRepository.findById(idPermission).orElseThrow(() -> new ResourceNotFoundException(Permission.class, idPermission));
        this.updateData(oldPermission, updatePermission);
        return permissionRepository.save(oldPermission);
    }

    public void updateData(Permission oldPermission, Permission updatePermission) {
        oldPermission.setDescription(updatePermission.getDescription());
    }

}
