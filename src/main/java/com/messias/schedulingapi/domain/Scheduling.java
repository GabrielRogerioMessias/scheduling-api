package com.messias.schedulingapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity
public class Scheduling {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDate dateScheduling;
    private LocalDateTime startOfService;
    private LocalDateTime endOfService;

    @ManyToOne
    @JoinColumn(name = "idDoctor")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "idBranch")
    private Branch branch;

    @ManyToOne
    @JoinColumn(name = "idUser")
    private User user;
    @JsonIgnore
    @OneToMany(mappedBy = "scheduling")
    private List<SchedulingEmployer> schedulingEmployerList = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "id_scheduling_info")
    private SchedulingInfo schedulingInfo;

    public Scheduling() {
    }

    public Scheduling(Integer id, LocalDate dateScheduling, LocalDateTime startOfService, LocalDateTime endOfService) {
        this.id = id;
        this.dateScheduling = dateScheduling;
        this.startOfService = startOfService;
        this.endOfService = endOfService;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDateScheduling() {
        return dateScheduling;
    }

    public void setDateScheduling(LocalDate dateScheduling) {
        this.dateScheduling = dateScheduling;
    }

    public LocalDateTime getStartOfService() {
        return startOfService;
    }

    public void setStartOfService(LocalDateTime startOfService) {
        this.startOfService = startOfService;
    }

    public LocalDateTime getEndOfService() {
        return endOfService;
    }

    public void setEndOfService(LocalDateTime endOfService) {
        this.endOfService = endOfService;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setSchedulingEmployerList(List<SchedulingEmployer> schedulingEmployerList) {
        this.schedulingEmployerList = schedulingEmployerList;
    }

    public List<SchedulingEmployer> getSchedulingEmployerList() {
        return schedulingEmployerList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Scheduling that = (Scheduling) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
