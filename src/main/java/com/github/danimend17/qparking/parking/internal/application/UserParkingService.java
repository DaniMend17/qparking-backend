package com.github.danimend17.qparking.parking.internal.application;

import com.github.danimend17.qparking.parking.ParkingDto;

public interface UserParkingService {

    ParkingDto getUserParking(Long userId);

    Long getUserParkingId(Long userId);
}
