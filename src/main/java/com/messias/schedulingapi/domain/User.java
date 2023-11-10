package com.messias.schedulingapi.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "tb_User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String login;
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Scheduling> schedulingList = new ArrayList<>();

}
