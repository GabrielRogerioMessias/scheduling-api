package com.messias.schedulingapi.domain;

import com.messias.schedulingapi.domain.pk.SchedulingEmployerPK;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalTime;
import java.util.Objects;


@Entity
@Table(name = "scheduling_employer")
public class SchedulingEmployer {
    @EmbeddedId
    private SchedulingEmployerPK idSchedulingEmployer;
    private LocalTime hour;
    private boolean presence;

    @ManyToOne
    @JoinColumn(name = "id_typeScheduling")
    private TypeScheduling typeScheduling;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    public SchedulingEmployer() {
    }

    public SchedulingEmployer(LocalTime hour, boolean presence) {
        this.hour = hour;
        this.presence = presence;
    }

    public Employer getEmployer() {
        return idSchedulingEmployer.getEmployer();
    }

    public void setEmployer(Employer employer) {
        this.idSchedulingEmployer.setEmployer(employer);
    }

    public Scheduling getScheduling() {
        return idSchedulingEmployer.getScheduling();
    }

    public void setScheduling(Scheduling scheduling) {
        this.idSchedulingEmployer.setScheduling(scheduling);
    }


    public LocalTime getHour() {
        return hour;
    }

    public void setHour(LocalTime hour) {
        this.hour = hour;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SchedulingEmployer that = (SchedulingEmployer) o;
        return Objects.equals(idSchedulingEmployer, that.idSchedulingEmployer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSchedulingEmployer);
    }
}
