package com.messias.schedulingapi.repositories;

import com.messias.schedulingapi.domain.Branch;
import com.messias.schedulingapi.domain.SchedulingInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchedulingInfoRepository extends JpaRepository<SchedulingInfo, Integer> {
}

