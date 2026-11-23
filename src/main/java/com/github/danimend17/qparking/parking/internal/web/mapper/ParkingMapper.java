package com.github.danimend17.qparking.parking.internal.web.mapper;

import com.github.danimend17.qparking.parking.ParkingDto;
import com.github.danimend17.qparking.parking.internal.domain.Parking;
import com.github.danimend17.qparking.parking.internal.web.ParkingRequest;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(
        componentModel = "spring",
        uses = ParkingScheduleMapper.class
)
public interface ParkingMapper {

    @Mapping(target = "parkingId", ignore = true)
    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Parking toModel(ParkingRequest request);

    ParkingDto toDto(Parking entity);

    @AfterMapping
    default void addParkingScheduleReference(@MappingTarget Parking parking) {
        if (parking.getParkingSchedule() != null) {
            parking.getParkingSchedule().forEach(parkingSchedule ->
                    parkingSchedule.setParking(parking));
        }
    }

}
