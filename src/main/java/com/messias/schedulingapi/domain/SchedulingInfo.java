package com.messias.schedulingapi.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class SchedulingInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private boolean hasScheduling;
    private String description;
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
}
