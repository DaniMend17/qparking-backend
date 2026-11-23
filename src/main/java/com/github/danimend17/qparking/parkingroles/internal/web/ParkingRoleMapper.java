package com.github.danimend17.qparking.parkingroles.internal.web;

import com.github.danimend17.qparking.parkingroles.ParkingRoleDto;
import com.github.danimend17.qparking.parkingroles.internal.domain.ParkingRole;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ParkingRoleMapper {

    @Mapping(target = "parkingRoleId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    ParkingRole fromRequestToModel(ParkingRoleRequest request);

    ParkingRoleDto fromModelToDto(ParkingRole parkingRole);
}
