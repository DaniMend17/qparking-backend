package com.github.danimend17.qparking.parking;

import org.springframework.data.domain.Page;

public interface IParkingQueryService {

    Page<ParkingDTO> getParkings(int pageNumber);

    ParkingDTO getParkingById(Long parkingId);
}
