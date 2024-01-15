package com.messias.schedulingapi.repositories;

import com.messias.schedulingapi.domain.Permission;
import jakarta.persistence.EntityManager;
import jakarta.servlet.http.PushBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class PermissionRepositoryTest {
    @Autowired
    PermissionRepository permissionRepository;
    @Autowired
    EntityManager entityManager;

    @Test
    void findPermissionByDescription() {
        String description = "ADMIN";
        Permission permission = new Permission(1, "ADMIN");
        this.insert(permission);
        Permission result = this.permissionRepository.findPermissionByDescription(description).get();
        assertEquals(permission, result);
    }

    public Permission insert(Permission newPermission) {
        Permission permission = new Permission();
        permission.setDescription(newPermission.getDescription());
        permission.setId(newPermission.getId());
        entityManager.merge(permission);
        return permission;
    }
}