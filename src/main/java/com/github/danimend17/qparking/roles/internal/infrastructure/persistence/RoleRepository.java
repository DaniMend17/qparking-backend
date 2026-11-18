package com.github.danimend17.qparking.roles.internal.infrastructure.persistence;

import com.github.danimend17.qparking.roles.internal.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    boolean existsByName(String name);

    Optional<Role> findByName(String name);

}
