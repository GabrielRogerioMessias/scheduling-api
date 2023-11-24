package com.messias.schedulingapi.domain;

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
    @OneToMany(mappedBy = "idSchedulingEmployer.employer")
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
