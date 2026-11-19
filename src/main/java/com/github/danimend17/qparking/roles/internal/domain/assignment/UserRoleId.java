package com.github.danimend17.qparking.roles.internal.domain.assignment;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleId {

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Long roleId;

}
