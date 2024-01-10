package com.messias.schedulingapi.repositories;

import com.messias.schedulingapi.domain.SchedulingEmployer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchedulingEmployerRepository extends JpaRepository<SchedulingEmployer, Integer> {

}
