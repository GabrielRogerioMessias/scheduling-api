package com.messias.schedulingapi.domain;

import jakarta.persistence.*;
import lombok.Data;

import javax.print.Doc;
import java.time.LocalDateTime;

@Data
@Entity
public class Scheduling {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDateTime dateScheduling;

    @ManyToOne
    @JoinColumn(name = "idDoctor")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "idBranch")
    private Branch branch;


    @ManyToOne
    @JoinColumn(name = "idUser")
    private User user;

}
