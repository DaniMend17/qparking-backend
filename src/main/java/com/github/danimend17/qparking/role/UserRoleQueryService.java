package com.github.danimend17.qparking.role;

import java.util.List;

public interface UserRoleQueryService {
    List<UserRoleDto> getUserRoles(Long userId);
}
