package com.messias.schedulingapi.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class TypeScheduling {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTypeScheduling;
    private String description;
}
