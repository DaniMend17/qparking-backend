package com.github.danimend17.qparking.roles.internal.application;

import com.github.danimend17.qparking.roles.RoleDto;
import com.github.danimend17.qparking.roles.internal.web.RoleRequest;

public interface RoleCommandService {
    RoleDto createRole(RoleRequest roleRequest);

    RoleDto updateRole(Long roleId, RoleRequest roleRequest);

    void
    deleteRole(Long roleId);
}
