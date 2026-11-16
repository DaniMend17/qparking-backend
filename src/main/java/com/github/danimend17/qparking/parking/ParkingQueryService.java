package com.github.danimend17.qparking.parking;

import org.springframework.data.domain.Page;

public interface ParkingQueryService {

    Page<ParkingDTO> getParkings(int pageNumber);

    ParkingDTO getParkingById(Long parkingId);

    boolean userHasAlreadyRegisterAParking(Long userId);
}
