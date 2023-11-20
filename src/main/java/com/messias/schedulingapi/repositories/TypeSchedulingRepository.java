package com.messias.schedulingapi.repositories;

import com.messias.schedulingapi.domain.TypeScheduling;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeSchedulingRepository extends JpaRepository<TypeScheduling, Integer> {
}

