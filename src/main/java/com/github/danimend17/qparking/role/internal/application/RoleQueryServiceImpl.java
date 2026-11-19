package com.github.danimend17.qparking.role.internal.application;

import com.github.danimend17.qparking.role.RoleDto;
import com.github.danimend17.qparking.role.RoleNameNotFoundException;
import com.github.danimend17.qparking.role.RoleNotFoundException;
import com.github.danimend17.qparking.role.RoleQueryService;
import com.github.danimend17.qparking.role.internal.web.RoleMapper;
import com.github.danimend17.qparking.role.internal.infrastructure.persistence.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RoleQueryServiceImpl implements RoleQueryService {

    private final RoleRepository roleRepository;
    private final RoleMapper mapper;

    @Override
    public RoleDto getRoleById(Long roleId) {
        return roleRepository.findById(roleId)
                .map(mapper::fromModelToDto)
                .orElseThrow(() -> new RoleNotFoundException(roleId));
    }

    @Override
    public RoleDto getRoleByName(String roleName) {
        return roleRepository.findByName(roleName)
                .map(mapper::fromModelToDto)
                .orElseThrow(() -> new RoleNameNotFoundException(roleName));
    }

    @Override
    public List<RoleDto> getAllRoles() {
        return roleRepository.findAll().stream()
                .map(mapper::fromModelToDto)
                .toList();
    }
}
