package com.github.danimend17.qparking.roles.internal.application;

import com.github.danimend17.qparking.roles.RoleDto;
import com.github.danimend17.qparking.roles.RoleNameNotFoundException;
import com.github.danimend17.qparking.roles.RoleNotFoundException;
import com.github.danimend17.qparking.roles.RoleQueryService;
import com.github.danimend17.qparking.roles.internal.web.RoleMapper;
import com.github.danimend17.qparking.roles.internal.RoleRepository;
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
