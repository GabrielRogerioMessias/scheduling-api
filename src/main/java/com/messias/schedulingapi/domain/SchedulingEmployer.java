package com.messias.schedulingapi.domain;

import com.messias.schedulingapi.domain.pk.SchedulingEmployerPK;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalTime;

@Data
@Entity
@Table(name = "scheduling_employer")
public class SchedulingEmployer {
    @EmbeddedId
    private SchedulingEmployerPK idSchedulingEmployer;
    private LocalTime hour;
    private boolean presence;

    @ManyToOne
    @JoinColumn(name = "id_typeScheduling")
    private TypeScheduling typeScheduling;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;
    

}
