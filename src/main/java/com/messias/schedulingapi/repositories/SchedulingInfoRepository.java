package com.messias.schedulingapi.repositories;


import com.messias.schedulingapi.domain.SchedulingInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SchedulingInfoRepository extends JpaRepository<SchedulingInfo, Integer> {
    @Query("SELECT si.hasScheduling from SchedulingInfo si where si.id = :idSchedulingInfo")
    Boolean findByHasScheduling(@Param("idSchedulingInfo") Integer idSchedulingInfo);
}

