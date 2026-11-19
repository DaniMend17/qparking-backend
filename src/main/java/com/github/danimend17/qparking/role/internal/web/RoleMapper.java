package com.github.danimend17.qparking.role.internal.web;

import com.github.danimend17.qparking.role.RoleDto;
import com.github.danimend17.qparking.role.internal.domain.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    @Mapping(target = "roleId", ignore = true)
    Role fromRequestToModel(RoleRequest request);

    RoleDto fromModelToDto(Role role);
}
