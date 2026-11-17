package com.github.danimend17.qparking.parkingroles.internal;

import com.github.danimend17.qparking.parking.ParkingNotFoundException;
import com.github.danimend17.qparking.parking.ParkingQueryService;
import com.github.danimend17.qparking.parkingroles.ParkingRoleDto;
import com.github.danimend17.qparking.parkingroles.ParkingRoleNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ParkingRoleCommandServiceImpl implements ParkingRoleCommandService {

    private final ParkingRoleRepository parkingRoleRepository;
    private final ParkingQueryService parkingQueryService;
    private final ParkingRoleMapper mapper;

    @Override
    public ParkingRoleDto createParkingRole(ParkingRoleRequest request) {
        if (!parkingQueryService.parkingExistsById(request.parkingId()))
            throw new ParkingNotFoundException(request.parkingId());

        if (parkingRoleRepository.roleNameExistsInParking(request.roleName(), request.parkingId()))
            throw new DuplicatedParkingRoleNameException(request.roleName());

        final ParkingRole parkingRole = mapper.fromRequestToModel(request);
        final ParkingRole savedParkingRole = parkingRoleRepository.save(parkingRole);

        return mapper.fromModelToDto(savedParkingRole);
    }

    @Override
    public ParkingRoleDto updateParkingRole(Long parkingRoleId, ParkingRoleRequest request) {
        final ParkingRole toUpdateParkingRole = parkingRoleRepository.findById(parkingRoleId)
                .orElseThrow(() -> new ParkingRoleNotFoundException(parkingRoleId));

        if (!request.roleName().equals(toUpdateParkingRole.getRoleName()) &&
                parkingRoleRepository.roleNameExistsInParking(request.roleName(), request.parkingId()))
            throw new DuplicatedParkingRoleNameException(request.roleName());

        final ParkingRole sourceParkingRole = mapper.fromRequestToModel(request);

        toUpdateParkingRole.updateDetails(sourceParkingRole);

        return mapper.fromModelToDto(parkingRoleRepository.save(toUpdateParkingRole));
    }

    @Override
    public void deleteParkingRole(Long parkingRoleId) {
        ParkingRole toDeleteParkingRole = parkingRoleRepository.findById(parkingRoleId)
                .orElseThrow(() -> new ParkingRoleNotFoundException(parkingRoleId));

        parkingRoleRepository.delete(toDeleteParkingRole);
    }
}
