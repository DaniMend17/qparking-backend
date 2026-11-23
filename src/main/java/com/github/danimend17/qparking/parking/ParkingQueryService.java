package com.github.danimend17.qparking.parking;

import org.springframework.data.domain.Page;

public interface ParkingQueryService {

    Page<ParkingDto> getParkings(int pageNumber);

    ParkingDto getParkingById(Long parkingId);

    boolean userHasAlreadyRegisterAParking(Long userId);

    boolean parkingExistsById(Long parkingId);

    ParkingDto getParkingByUserId(Long userId);

}
