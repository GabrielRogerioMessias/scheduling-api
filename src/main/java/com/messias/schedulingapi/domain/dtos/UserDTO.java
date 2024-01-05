package com.messias.schedulingapi.domain.dtos;

import java.util.List;

public class UserDTO {
    private String fullName;
    private String username;
    private String password;
    private List<Integer> permissionIds;

    public UserDTO() {
    }

    public UserDTO(String fullname, String username, String password, List<Integer> permissionIds) {
        this.fullName = fullname;
        this.username = username;
        this.password = password;
        this.permissionIds = permissionIds;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Integer> getPermissionIds() {
        return permissionIds;
    }

    public void setPermissionIds(List<Integer> permissionIds) {
        this.permissionIds = permissionIds;
    }
}
