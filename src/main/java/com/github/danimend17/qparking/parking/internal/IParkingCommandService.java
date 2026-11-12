package com.github.danimend17.qparking.parking.internal;

import com.github.danimend17.qparking.parking.ParkingDTO;

interface IParkingCommandService {
    ParkingDTO createParking(ParkingRequest request);

    ParkingDTO updateParking(Long parkingId, ParkingRequest request);

    void deleteParking(Long parkingId);

}
