package com.messias.schedulingapi.domain;

import com.messias.schedulingapi.domain.pk.SchedulingEmployerPK;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalTime;

@Entity
@Table(name = "scheduling_employer")
public class SchedulingEmployer {
    @EmbeddedId
    private SchedulingEmployerPK idSchedulingEmployer;
    private LocalTime hour;
    private boolean precense;
    

}
