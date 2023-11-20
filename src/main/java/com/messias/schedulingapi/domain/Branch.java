package com.messias.schedulingapi.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nameBranch;
    private String city;

    @OneToMany(mappedBy = "branch")
    private List<Scheduling> schedulingList = new ArrayList<>();

    public Branch() {
    }

    public Branch(Integer id, String nameBranch, String city) {
        this.id = id;
        this.nameBranch = nameBranch;
        this.city = city;
    }

    public Integer getId() {
        return id;
    }

    public String getNameBranch() {
        return nameBranch;
    }

    public void setNameBranch(String nameBranch) {
        this.nameBranch = nameBranch;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<Scheduling> getSchedulingList() {
        return schedulingList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Branch branch = (Branch) o;
        return Objects.equals(id, branch.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
