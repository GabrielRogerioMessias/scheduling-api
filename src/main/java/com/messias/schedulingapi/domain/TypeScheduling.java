package com.messias.schedulingapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class TypeScheduling {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTypeScheduling;

    private String description;
    @JsonIgnore
    @OneToMany(mappedBy = "typeScheduling")
    private List<SchedulingEmployer> schedulingEmployers = new ArrayList<>();

    public TypeScheduling() {
    }

    public TypeScheduling(Integer idTypeScheduling, String description) {
        this.idTypeScheduling = idTypeScheduling;
        this.description = description;
    }

    public Integer getIdTypeScheduling() {
        return idTypeScheduling;
    }

    public void setIdTypeScheduling(Integer idTypeScheduling) {
        this.idTypeScheduling = idTypeScheduling;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<SchedulingEmployer> getSchedulingEmployers() {
        return schedulingEmployers;
    }

    public void setSchedulingEmployers(List<SchedulingEmployer> schedulingEmployers) {
        this.schedulingEmployers = schedulingEmployers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TypeScheduling that = (TypeScheduling) o;
        return Objects.equals(idTypeScheduling, that.idTypeScheduling);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTypeScheduling);
    }
}
