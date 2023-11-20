package com.messias.schedulingapi.domain;

import com.messias.schedulingapi.domain.pk.SchedulingEmployerPK;
import jakarta.persistence.*;
import lombok.Data;

import javax.print.Doc;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


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

    @OneToMany(mappedBy = "idSchedulingEmployer.scheduling")
    private List<SchedulingEmployer> schedulingEmployerList = new ArrayList<>();

    public Scheduling() {
    }

    public Scheduling(Integer id, LocalDateTime dateScheduling, Doctor doctor, Branch branch) {
        this.id = id;
        this.dateScheduling = dateScheduling;
        this.doctor = doctor;
        this.branch = branch;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getDateScheduling() {
        return dateScheduling;
    }

    public void setDateScheduling(LocalDateTime dateScheduling) {
        this.dateScheduling = dateScheduling;
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
        Scheduling that = (Scheduling) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
