package com.messias.schedulingapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
public class SchedulingInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private boolean hasScheduling;
    private String description;
    @JsonIgnore
    @OneToMany(mappedBy = "schedulingInfo")
    private List<Scheduling> schedulings;

    public SchedulingInfo() {
    }

    public SchedulingInfo(Integer id, boolean hasScheduling, String description) {
        this.id = id;
        this.hasScheduling = hasScheduling;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isHasScheduling() {
        return hasScheduling;
    }

    public void setHasScheduling(boolean hasScheduling) {
        this.hasScheduling = hasScheduling;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Scheduling> getSchedulings() {
        return schedulings;
    }

    public void setSchedulings(List<Scheduling> schedulings) {
        this.schedulings = schedulings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SchedulingInfo that = (SchedulingInfo) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
