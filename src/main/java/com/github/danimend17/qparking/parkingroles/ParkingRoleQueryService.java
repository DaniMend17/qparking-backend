package com.github.danimend17.qparking.parkingroles;

import org.springframework.data.domain.Page;

public interface ParkingRoleQueryService {

    Page<ParkingRoleDto> getAllParkingRoles(int pageNumber);

    ParkingRoleDto getParkingRoleById(Long parkingRoleId);

    Page<ParkingRoleDto> getParkingRolesOfParkingWithId(Long parkingId, int pageNumber);

    Page<String> getParkingRoleNamesOfParkingWithId(Long parkingId, int pageNumber);

}
