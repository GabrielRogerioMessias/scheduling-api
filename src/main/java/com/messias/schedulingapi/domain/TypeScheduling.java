package com.messias.schedulingapi.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class TypeScheduling {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTypeScheduling;
    private String description;

    @OneToMany(mappedBy = "typeScheduling")
    private List<SchedulingEmployer> schedulingEmployers = new ArrayList<SchedulingEmployer>();


}
