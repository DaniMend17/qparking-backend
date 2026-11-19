package com.github.danimend17.qparking.role.internal.application.assignment;

import com.github.danimend17.qparking.role.RoleQueryService;
import com.github.danimend17.qparking.role.UserRoleDto;
import com.github.danimend17.qparking.role.UserRoleQueryService;
import com.github.danimend17.qparking.role.internal.infrastructure.persistence.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserRoleQueryServiceImpl implements UserRoleQueryService {

    private final UserRoleRepository userRoleRepository;
    private final RoleQueryService roleQueryService;

    @Override
    public List<UserRoleDto> getUserRoles(Long userId) {
        return userRoleRepository.findAllByUserId(userId).stream()
                .map(userRole -> new UserRoleDto(
                        userRole.getId().getRoleId(),
                        roleQueryService.getRoleById(userRole.getId().getRoleId()).name()
                ))
                .toList();
    }

}
