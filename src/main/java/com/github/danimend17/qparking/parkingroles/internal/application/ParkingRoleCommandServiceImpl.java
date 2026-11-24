package com.github.danimend17.qparking.parkingroles.internal.application;

import com.github.danimend17.qparking.parking.UserParkingService;
import com.github.danimend17.qparking.parkingroles.ParkingRoleDto;
import com.github.danimend17.qparking.parkingroles.ParkingRoleNotFoundException;
import com.github.danimend17.qparking.parkingroles.internal.web.ParkingRoleMapper;
import com.github.danimend17.qparking.parkingroles.internal.infrastructure.persistence.ParkingRoleRepository;
import com.github.danimend17.qparking.parkingroles.internal.web.ParkingRoleRequest;
import com.github.danimend17.qparking.parkingroles.internal.domain.DuplicatedParkingRoleNameException;
import com.github.danimend17.qparking.parkingroles.internal.domain.ParkingRole;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ParkingRoleCommandServiceImpl implements ParkingRoleCommandService {

    private final ParkingRoleRepository parkingRoleRepository;
    private final UserParkingService userParkingService;
    private final ParkingRoleMapper mapper;

    @Override
    public ParkingRoleDto createParkingRole(ParkingRoleRequest request) {
        final Long userParkingId = userParkingService.getUserParkingId();

        if (parkingRoleRepository.roleNameExistsInParking(request.roleName(), userParkingId))
            throw new DuplicatedParkingRoleNameException(request.roleName());

        final ParkingRole parkingRole = mapper.fromRequestToModel(request);
        parkingRole.assignParking(userParkingId);

        final ParkingRole savedParkingRole = parkingRoleRepository.save(parkingRole);

        return mapper.fromModelToDto(savedParkingRole);
    }

    @Override
    public ParkingRoleDto updateParkingRole(Long parkingRoleId, ParkingRoleRequest request) {
        final Long userParkingId = userParkingService.getUserParkingId();

        final ParkingRole parkingRole = parkingRoleRepository
                .findByParkingIdAndParkingRoleId(userParkingId, parkingRoleId)
                .orElseThrow(() -> new ParkingRoleNotFoundException(parkingRoleId));

        if (!request.roleName().equals(parkingRole.getRoleName()) &&
                parkingRoleRepository.roleNameExistsInParking(request.roleName(), userParkingId))
            throw new DuplicatedParkingRoleNameException(request.roleName());

        final ParkingRole source = mapper.fromRequestToModel(request);

        parkingRole.updateDetails(source);

        return mapper.fromModelToDto(parkingRoleRepository.save(parkingRole));
    }

    @Override
    public void deleteParkingRole(Long parkingRoleId) {
        final Long userParkingId = userParkingService.getUserParkingId();

        final ParkingRole parkingRole = parkingRoleRepository
                .findByParkingIdAndParkingRoleId(userParkingId, parkingRoleId)
                .orElseThrow(() -> new ParkingRoleNotFoundException(parkingRoleId));

        parkingRoleRepository.delete(parkingRole);
    }
}
