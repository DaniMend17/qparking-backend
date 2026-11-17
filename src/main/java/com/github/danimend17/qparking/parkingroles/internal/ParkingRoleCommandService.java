package com.github.danimend17.qparking.parkingroles.internal;

import com.github.danimend17.qparking.parkingroles.ParkingRoleDto;

public interface ParkingRoleCommandService {

    ParkingRoleDto createParkingRole(ParkingRoleRequest request);

    ParkingRoleDto updateParkingRole(Long parkingRoleId, ParkingRoleRequest request);

    void deleteParkingRole(Long parkingRoleId);

}
