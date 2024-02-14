package com.messias.schedulingapi.services;

import com.messias.schedulingapi.domain.Permission;
import com.messias.schedulingapi.repositories.PermissionRepository;
import com.messias.schedulingapi.services.exceptionsServices.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.parameters.P;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class PermissionServiceTest {
    @InjectMocks
    PermissionService permissionService;
    @Mock
    PermissionRepository permissionRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void findAll() {
        List<Permission> permissionList = new ArrayList<>();
        Permission permission1 = new Permission(1, "TEST");
        Permission permission2 = new Permission(2, "TEST");
        Permission permission3 = new Permission(3, "TEST");
        Permission permission4 = new Permission(4, "TEST");

        permissionList.add(permission1);
        permissionList.add(permission2);
        permissionList.add(permission3);
        permissionList.add(permission4);

        when(permissionRepository.findAll()).thenReturn(permissionList);
        List<Permission> result = permissionService.findAll();

        verify(permissionRepository).findAll();
        assertNotNull(result);
        assertEquals(permissionList, result);
        assertEquals(permissionList.size(), result.size());
        assertEquals(permissionList.contains(permission1), result.contains(permission1));
    }

    @Test
    void findByDescriptionCase1() {
        String param = "TEST";
        Permission permission = new Permission(1, "TEST");
        when(permissionRepository.findPermissionByDescription(param)).thenReturn(Optional.of(permission));
        Permission result = permissionService.findByDescription(param);
        assertAll(
                () -> assertEquals(permission, result),
                () -> assertEquals(permission.getDescription(), result.getDescription()),
                () -> verify(permissionRepository).findPermissionByDescription(param)
        );
    }

    @Test
    void findByDescriptionCase2() {
        String description = "";
        when(permissionRepository.findPermissionByDescription(description)).thenReturn(Optional.empty());

        assertAll(
                () -> assertThrows(ResourceNotFoundException.class, () -> permissionService.findByDescription(description)),
                () -> verify(permissionRepository).findPermissionByDescription(description)
        );
    }

    @Test
    void findById() {
        Integer idPermission = 1;
        Permission permission = new Permission(idPermission, "TEST");
        when(permissionRepository.findById(idPermission)).thenReturn(Optional.of(permission));

        Permission result = permissionService.findById(idPermission);

        assertAll(
                () -> assertEquals(permission, result),
                () -> assertEquals(permission.getDescription(), result.getDescription()),
                () -> verify(permissionRepository).findById(idPermission)
        );
    }

    @Test
    void delete() {
    }

    @Test
    void insert() {
    }

    @Test
    void update() {
    }

    @Test
    void updateData() {
    }
}