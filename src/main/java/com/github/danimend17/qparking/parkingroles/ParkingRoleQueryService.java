package com.github.danimend17.qparking.parkingroles;

import com.github.danimend17.qparking.shared.models.PageResult;

public interface ParkingRoleQueryService {

    PageResult<ParkingRoleDto> getAllParkingRoles(int pageNumber);

    ParkingRoleDto getParkingRoleById(Long parkingRoleId);

    PageResult<ParkingRoleDto> getParkingRolesOfParking(Long parkingId, int pageNumber);

    PageResult<ParkingRoleDto> getParkingRolesOfUserParking(Long userId, int pageNumber);

    PageResult<String> getParkingRoleNamesOfParking(Long parkingId, int pageNumber);

}
