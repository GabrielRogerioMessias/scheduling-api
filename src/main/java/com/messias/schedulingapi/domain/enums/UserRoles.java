package com.messias.schedulingapi.domain.enums;

public enum UserRoles {
    ADMIN("admin"),
    RH_USER("user_rh"),
    USER_OUTPATIENT("user_outpatient");
    private String role;

    UserRoles(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }


}
