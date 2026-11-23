package com.github.danimend17.qparking.role.internal.infrastructure.persistence;

import com.github.danimend17.qparking.role.internal.domain.assignment.UserRole;
import com.github.danimend17.qparking.role.internal.domain.assignment.UserRoleId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRoleRepository extends JpaRepository<UserRole, UserRoleId> {

    @Query("""
                SELECT ur
                FROM UserRole ur
                WHERE ur.id.userId = :userId
            """)
    List<UserRole> findAllByUserId(@Param("userId") Long userId);

}
