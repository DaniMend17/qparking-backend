package com.github.danimend17.qparking.parking.internal.mapper;

import com.github.danimend17.qparking.parking.ParkingDTO;
import com.github.danimend17.qparking.parking.internal.ParkingEntity;
import com.github.danimend17.qparking.parking.internal.ParkingRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ParkingMapper {

    @Mapping(target = "parkingId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "parkingSchedules", ignore = true)
    ParkingEntity fromRequestToEntity(ParkingRequest request);

    ParkingDTO fromEntityToDto(ParkingEntity entity);

}
