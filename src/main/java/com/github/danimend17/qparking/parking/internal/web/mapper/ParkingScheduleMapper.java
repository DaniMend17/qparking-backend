package com.github.danimend17.qparking.parking.internal.web.mapper;

import com.github.danimend17.qparking.parking.ParkingScheduleDto;
import com.github.danimend17.qparking.parking.internal.domain.ParkingSchedule;
import com.github.danimend17.qparking.parking.internal.web.ParkingScheduleRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ParkingScheduleMapper {

    @Mapping(target = "parkingScheduleId", ignore = true)
    @Mapping(target = "parking", ignore = true)
    ParkingSchedule toModel(ParkingScheduleRequest request);

    ParkingScheduleDto toDto(ParkingSchedule entity);

}
