package com.messias.schedulingapi.domain;


import jakarta.persistence.*;


import java.time.LocalTime;
import java.util.Objects;


@Entity
@Table(name = "scheduling_employer")
public class SchedulingEmployer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalTime serviceTime;

    private boolean presence;

    @ManyToOne
    @JoinColumn(name = "id_typeScheduling")
    private TypeScheduling typeScheduling;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;
    @ManyToOne
    @JoinColumn(name = "id_employer")
    private Employer employer;

    @ManyToOne
    @JoinColumn(name = "id_scheduling")
    private Scheduling scheduling;

    public SchedulingEmployer() {
    }

    public SchedulingEmployer(Integer id, LocalTime serviceTime, boolean presence) {
        this.id = id;
        this.serviceTime = serviceTime;
        this.presence = presence;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalTime getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(LocalTime serviceTime) {
        this.serviceTime = serviceTime;
    }

    public boolean isPresence() {
        return presence;
    }

    public void setPresence(boolean presence) {
        this.presence = presence;
    }

    public TypeScheduling getTypeScheduling() {
        return typeScheduling;
    }

    public void setTypeScheduling(TypeScheduling typeScheduling) {
        this.typeScheduling = typeScheduling;
    }

    public Employer getEmployer() {
        return employer;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }

    public Scheduling getScheduling() {
        return scheduling;
    }

    public void setScheduling(Scheduling scheduling) {
        this.scheduling = scheduling;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SchedulingEmployer that = (SchedulingEmployer) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
