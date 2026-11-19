package com.github.danimend17.qparking.role.internal.domain.assignment;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleId {

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Long roleId;

}
