package com.github.danimend17.qparking.role.internal.infrastructure.persistence;

import com.github.danimend17.qparking.role.internal.domain.assignment.UserRole;
import com.github.danimend17.qparking.role.internal.domain.assignment.UserRoleId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, UserRoleId> {
}
