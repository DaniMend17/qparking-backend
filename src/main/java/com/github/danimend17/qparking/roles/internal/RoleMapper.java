package com.github.danimend17.qparking.roles.internal;

import com.github.danimend17.qparking.roles.RoleDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    @Mapping(target = "roleId", ignore = true)
    Role fromRequestToModel(RoleRequest request);

    RoleDto fromModelToDto(Role role);
}
