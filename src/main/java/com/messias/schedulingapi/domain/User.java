package com.messias.schedulingapi.domain;

import com.messias.schedulingapi.domain.enums.UserRoles;
import jakarta.persistence.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "tb_User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String login;
    private String password;
    @Enumerated(EnumType.STRING)
    private UserRoles roles;

    @OneToMany(mappedBy = "user")
    private List<Scheduling> schedulingList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<SchedulingEmployer> schedulingEmployerList = new ArrayList<>();

    public User() {
    }

    public User(Integer id, String name, String login, String password) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Scheduling> getSchedulingList() {
        return schedulingList;
    }

    public void setSchedulingList(List<Scheduling> schedulingList) {
        this.schedulingList = schedulingList;
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
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
