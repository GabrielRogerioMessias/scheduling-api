package com.messias.schedulingapi.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;

import java.util.ArrayList;
import java.util.List;


@Entity
@Data
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String CRM;

    @OneToMany(mappedBy = "doctor")
    private List<Scheduling> schedulingList = new ArrayList<>();
}
