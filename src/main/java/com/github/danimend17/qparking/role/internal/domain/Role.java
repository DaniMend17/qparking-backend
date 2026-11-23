package com.github.danimend17.qparking.role.internal.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "roles")
@Getter
@Setter
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;

    @Column(name = "role_name", length = 10, unique = true, nullable = false)
    private String name;

    @Column(length = 50, nullable = false)
    private String description;

    public void updateDetails(Role sourceRole) {
        this.name = sourceRole.name;
        this.description = sourceRole.description;
    }
}
