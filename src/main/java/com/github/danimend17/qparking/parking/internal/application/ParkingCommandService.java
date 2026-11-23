package com.github.danimend17.qparking.parking.internal.application;

import com.github.danimend17.qparking.parking.ParkingDto;
import com.github.danimend17.qparking.parking.internal.web.ParkingRequest;

public interface ParkingCommandService {
    ParkingDto createParking(ParkingRequest request);

    ParkingDto updateParking(ParkingRequest request);

    void deleteParking();

}
