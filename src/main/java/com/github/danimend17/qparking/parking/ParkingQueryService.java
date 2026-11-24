package com.github.danimend17.qparking.parking;

import com.github.danimend17.qparking.shared.models.PageResult;

public interface ParkingQueryService {

    PageResult<ParkingDto> getAllParkings(int pageNumber);

    ParkingDto getParkingById(Long parkingId);

    ParkingDto getParkingByUserId(Long userId);

}
