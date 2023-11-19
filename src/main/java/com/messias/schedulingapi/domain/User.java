package com.messias.schedulingapi.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "tb_User")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String login;
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Scheduling> schedulingList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<SchedulingEmployer> schedulingEmployerList = new ArrayList<>();

}
