package com.github.danimend17.qparking.parking;

import com.github.danimend17.qparking.shared.models.PageResult;

public interface ParkingQueryService {

    PageResult<ParkingDto> getParkings(int pageNumber);

    ParkingDto getParkingById(Long parkingId);

    boolean userHasAlreadyRegisterAParking(Long userId);

    boolean parkingExistsById(Long parkingId);

    ParkingDto getParkingByUserId(Long userId);

    Long getUserParkingId(Long userId);

}
