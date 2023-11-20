package com.messias.schedulingapi.domain.pk;

import com.messias.schedulingapi.domain.Employer;
import com.messias.schedulingapi.domain.Scheduling;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class SchedulingEmployerPK implements Serializable {

    @ManyToOne
    @JoinColumn(name = "id_Scheduling")
    private Scheduling scheduling;
    @ManyToOne
    @JoinColumn(name = "id_employer")
    private Employer employer;

    public Scheduling getScheduling() {
        return scheduling;
    }

    public void setScheduling(Scheduling scheduling) {
        this.scheduling = scheduling;
    }

    public Employer getEmployer() {
        return employer;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SchedulingEmployerPK that = (SchedulingEmployerPK) o;
        return Objects.equals(scheduling, that.scheduling) && Objects.equals(employer, that.employer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(scheduling, employer);
    }
}
