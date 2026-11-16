package com.github.danimend17.qparking.parking.internal.web.mapper;

import com.github.danimend17.qparking.parking.ParkingDTO;
import com.github.danimend17.qparking.parking.internal.domain.Parking;
import com.github.danimend17.qparking.parking.internal.web.ParkingRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ParkingMapper {

    @Mapping(target = "parkingId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "parkingSchedules", ignore = true)
    Parking fromRequestToEntity(ParkingRequest request);

    ParkingDTO fromEntityToDto(Parking entity);

}
