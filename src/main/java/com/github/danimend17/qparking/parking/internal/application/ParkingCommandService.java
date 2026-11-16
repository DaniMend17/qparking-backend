package com.github.danimend17.qparking.parking.internal.application;

import com.github.danimend17.qparking.parking.ParkingDTO;
import com.github.danimend17.qparking.parking.internal.web.ParkingRequest;

public interface ParkingCommandService {
    ParkingDTO createParking(ParkingRequest request);

    ParkingDTO updateParking(Long parkingId, ParkingRequest request);

    void deleteParking(Long parkingId);

}
