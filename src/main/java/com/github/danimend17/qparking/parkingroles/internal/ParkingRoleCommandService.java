package com.github.danimend17.qparking.parkingroles.internal;

import com.github.danimend17.qparking.parkingroles.ParkingRoleDto;

public interface ParkingRoleCommandService {

    ParkingRoleDto createParkingRole(Long userId, ParkingRoleRequest request);

    ParkingRoleDto updateParkingRole(Long userId, Long parkingRoleId, ParkingRoleRequest request);

    void deleteParkingRole(Long userId, Long parkingRoleId);

}
