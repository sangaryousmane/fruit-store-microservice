package com.ousmane.authenticationservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity(name = "Role")
@Table(name = "Roles")
@Getter
@Setter
public class Role extends BaseEntity implements Serializable {

    @Enumerated(EnumType.STRING)
    @Column(length = 20, unique = true)
    private RoleType name;

    public Role() {
    }

    public Role(RoleType name) {
        this.name = name;
    }
}
