package com.messias.schedulingapi.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nameBranch;
    private String city;

    @OneToMany(mappedBy = "branch")
    private List<Scheduling> schedulingList = new ArrayList<>();
}
