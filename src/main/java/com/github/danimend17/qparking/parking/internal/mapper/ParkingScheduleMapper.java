package com.github.danimend17.qparking.parking.internal.mapper;

import com.github.danimend17.qparking.parking.ParkingScheduleDTO;
import com.github.danimend17.qparking.parking.internal.ParkingScheduleEntity;
import com.github.danimend17.qparking.parking.internal.ParkingScheduleRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ParkingScheduleMapper {

    ParkingScheduleEntity fromRequestToEntity(ParkingScheduleRequest request);

    ParkingScheduleDTO fromEntityToDto(ParkingScheduleEntity entity);

}
