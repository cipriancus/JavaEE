package com.java.model;

import javax.persistence.*;

import javax.persistence.Entity;

@Entity
@Cacheable
@Table(name = "Roles")
public class Role extends BaseEntity{
    @Column(name = "ROLE_TYPE")
    private String roleType;

    @Column(name = "DESCRIPTION")
    private String description;

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
