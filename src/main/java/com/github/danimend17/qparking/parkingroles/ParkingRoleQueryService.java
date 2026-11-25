package com.github.danimend17.qparking.parkingroles;

import com.github.danimend17.qparking.shared.models.PageResult;

public interface ParkingRoleQueryService {

    PageResult<ParkingRoleDto> getAllParkingRoles(int pageNumber);

    ParkingRoleDto getParkingRoleById(Long parkingRoleId);

    PageResult<ParkingRoleDto> getParkingRolesByParkingId(Long parkingId, int pageNumber);

    PageResult<ParkingRoleDto> getUserParkingRoles(int pageNumber);

    PageResult<String> getRoleNamesByParkingId(Long parkingId, int pageNumber);

    ParkingRoleDto getParkingRoleByName(Long parkingId, String roleName);

}
