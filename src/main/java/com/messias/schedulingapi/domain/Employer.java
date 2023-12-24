package com.messias.schedulingapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity
public class Employer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @JsonIgnore
    @OneToMany(mappedBy = "employer")
    private List<SchedulingEmployer> schedulingEmployerList = new ArrayList<>();

    public Employer() {
    }

    public Employer(Integer id, String name, List<SchedulingEmployer> schedulingEmployerList) {
        this.id = id;
        this.name = name;
        this.schedulingEmployerList = schedulingEmployerList;
    }

    public List<SchedulingEmployer> getSchedulingEmployerList() {
        return schedulingEmployerList;
    }

    public void setSchedulingEmployerList(List<SchedulingEmployer> schedulingEmployerList) {
        this.schedulingEmployerList = schedulingEmployerList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employer employer = (Employer) o;
        return Objects.equals(id, employer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
