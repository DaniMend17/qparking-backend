package com.github.danimend17.qparking.roles.internal;

import com.github.danimend17.qparking.roles.RoleDto;

public interface RoleCommandService {
    RoleDto createRole(RoleRequest roleRequest);

    RoleDto updateRole(Long roleId, RoleRequest roleRequest);

    void
    deleteRole(Long roleId);
}
