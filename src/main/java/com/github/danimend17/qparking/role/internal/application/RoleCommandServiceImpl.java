package com.github.danimend17.qparking.role.internal.application;

import com.github.danimend17.qparking.role.RoleDto;
import com.github.danimend17.qparking.role.RoleNotFoundException;
import com.github.danimend17.qparking.role.internal.web.RoleMapper;
import com.github.danimend17.qparking.role.internal.infrastructure.persistence.RoleRepository;
import com.github.danimend17.qparking.role.internal.web.RoleRequest;
import com.github.danimend17.qparking.role.internal.domain.DuplicatedRoleNameException;
import com.github.danimend17.qparking.role.internal.domain.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class RoleCommandServiceImpl implements RoleCommandService {

    private final RoleRepository roleRepository;
    private final RoleMapper mapper;

    @Override
    public RoleDto createRole(RoleRequest roleRequest) {
        if (roleRepository.existsByName(roleRequest.name()))
            throw new DuplicatedRoleNameException(roleRequest.name());

        final Role savedRole = roleRepository.save(mapper.fromRequestToModel(roleRequest));

        return mapper.fromModelToDto(savedRole);
    }

    @Override
    public RoleDto updateRole(Long roleId, RoleRequest roleRequest) {
        final Role toUpdateRole = roleRepository.findById(roleId)
                .orElseThrow(() -> new RoleNotFoundException(roleId));

        if (!roleRequest.name().equals(toUpdateRole.getName()) &&
                roleRepository.existsByName(roleRequest.name())) {
            throw new DuplicatedRoleNameException(roleRequest.name());
        }

        toUpdateRole.updateDetails(mapper.fromRequestToModel(roleRequest));

        return mapper.fromModelToDto(roleRepository.save(toUpdateRole));
    }

    @Override
    public void deleteRole(Long roleId) {
        final Role toDeleteRole = roleRepository.findById(roleId)
                .orElseThrow(() -> new RoleNotFoundException(roleId));

        roleRepository.delete(toDeleteRole);
    }
}
