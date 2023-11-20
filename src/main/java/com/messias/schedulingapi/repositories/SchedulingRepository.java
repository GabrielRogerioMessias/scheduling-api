package com.messias.schedulingapi.repositories;

import com.messias.schedulingapi.domain.Scheduling;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchedulingRepository extends JpaRepository<Scheduling, Integer> {
}
