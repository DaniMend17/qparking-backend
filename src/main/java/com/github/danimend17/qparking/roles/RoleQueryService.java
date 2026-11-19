package com.github.danimend17.qparking.roles;

import java.util.List;

public interface RoleQueryService {

    RoleDto getRoleById(Long roleId);

    RoleDto getRoleByName(String roleName);

    List<RoleDto> getAllRoles();

}
