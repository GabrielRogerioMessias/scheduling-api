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
import org.yaml.snakeyaml.events.Event;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
    void findByIdCase1() {
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
    void findByIdCase2() {
        Integer idPermission = 1;
        when(permissionRepository.findById(idPermission)).thenReturn(Optional.empty());
        assertAll(
                () -> assertThrows(ResourceNotFoundException.class, () -> permissionService.findById(idPermission)),
                () -> verify(permissionRepository).findById(idPermission)
        );
    }

    @Test
    void deleteCase1() {
        Integer idPermission = 1;
        Permission permission = new Permission(idPermission, "TEST");
        when(permissionRepository.findById(idPermission)).thenReturn(Optional.of(permission));
        doNothing().when(permissionRepository).delete(permission);

        assertAll(
                () -> assertDoesNotThrow(() -> permissionService.delete(idPermission)),
                () -> verify(permissionRepository).findById(idPermission),
                () -> verify(permissionRepository).delete(permission)
        );

    }

    @Test
    void deleteCase2() {
        Integer idPermission = 1;
        when(permissionRepository.findById(idPermission)).thenReturn(Optional.empty());
        assertAll(
                () -> assertThrows(ResourceNotFoundException.class, () -> permissionService.findById(idPermission)),
                () -> verify(permissionRepository).findById(idPermission),
                () -> verify(permissionRepository, never()).delete(any())
        );
    }

    @Test
    void insert() {
        Integer idPermission = 1;
        Permission permission = new Permission(idPermission, "TEST");

        when(permissionRepository.save(permission)).thenReturn(permission);

        Permission result = permissionService.insert(permission);
        assertAll(
                () -> assertNotNull(result),
                () -> verify(permissionRepository).save(permission),
                () -> assertEquals(permission, result)
        );
    }

    @Test
    void updateCase1() {
        Integer idPermission = 1;
        Permission oldPermission = new Permission(idPermission, "TEST");
        Permission updatePermission = new Permission(idPermission, "UPDATE TEST");
        when(permissionRepository.findById(idPermission)).thenReturn(Optional.of(oldPermission));
        when(permissionRepository.save(oldPermission)).thenReturn(oldPermission);

        Permission result = permissionService.update(idPermission, updatePermission);

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(oldPermission, updatePermission),
                () -> assertEquals(oldPermission.getDescription(), updatePermission.getDescription()),
                () -> verify(permissionRepository).findById(idPermission),
                () -> verify(permissionRepository).save(oldPermission)
        );
    }

    


}