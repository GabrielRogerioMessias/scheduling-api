package com.messias.schedulingapi.repositories;

import com.messias.schedulingapi.domain.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PermissionRepository extends JpaRepository<Permission, Integer> {
    @Query("SELECT p FROM Permission p WHERE p.description = :description")
    Optional<Permission> findPermissionByDescription(@Param(value = "description") String description);
}
