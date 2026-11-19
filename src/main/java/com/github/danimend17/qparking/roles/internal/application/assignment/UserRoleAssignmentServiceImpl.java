package com.github.danimend17.qparking.roles.internal.application.assignment;

import com.github.danimend17.qparking.roles.RoleQueryService;
import com.github.danimend17.qparking.roles.internal.domain.assignment.UserRole;
import com.github.danimend17.qparking.roles.internal.domain.assignment.UserRoleId;
import com.github.danimend17.qparking.roles.internal.infrastructure.persistence.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRoleAssignmentServiceImpl implements UserRoleAssignmentService {

    private final UserRoleRepository userRoleRepository;
    private final RoleQueryService roleQueryService;

    @Override
    public void assignDefaultRole(Long userId) {
        final Long rolId = roleQueryService.getRoleByName("ROLE_USER").roleId();
        final UserRole userRole = new UserRole(new UserRoleId(userId, rolId));
        userRoleRepository.save(userRole);
    }
}
