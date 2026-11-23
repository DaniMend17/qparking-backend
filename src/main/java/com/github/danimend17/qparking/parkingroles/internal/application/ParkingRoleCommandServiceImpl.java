package com.github.danimend17.qparking.parkingroles.internal.application;

import com.github.danimend17.qparking.parking.ParkingQueryService;
import com.github.danimend17.qparking.parkingroles.ParkingRoleDto;
import com.github.danimend17.qparking.parkingroles.ParkingRoleNotFoundException;
import com.github.danimend17.qparking.parkingroles.internal.web.ParkingRoleMapper;
import com.github.danimend17.qparking.parkingroles.internal.infrastructure.persistence.ParkingRoleRepository;
import com.github.danimend17.qparking.parkingroles.internal.web.ParkingRoleRequest;
import com.github.danimend17.qparking.parkingroles.internal.domain.DuplicatedParkingRoleNameException;
import com.github.danimend17.qparking.parkingroles.internal.domain.ParkingRole;
import com.github.danimend17.qparking.user.UserNotFoundException;
import com.github.danimend17.qparking.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ParkingRoleCommandServiceImpl implements ParkingRoleCommandService {

    private final ParkingRoleRepository parkingRoleRepository;
    private final ParkingQueryService parkingQueryService;
    private final UserService userService;
    private final ParkingRoleMapper mapper;

    @Override
    public ParkingRoleDto createParkingRole(Long userId, ParkingRoleRequest request) {
        if (!userService.userExistsById(userId)) throw new UserNotFoundException(userId);

        final Long userParkingId = parkingQueryService.getParkingByUserId(userId).parkingId();

        if (parkingRoleRepository.roleNameExistsInParking(request.roleName(), userParkingId))
            throw new DuplicatedParkingRoleNameException(request.roleName());

        final ParkingRole parkingRole = mapper.fromRequestToModel(request);
        parkingRole.assignParking(userParkingId);

        final ParkingRole savedParkingRole = parkingRoleRepository.save(parkingRole);

        return mapper.fromModelToDto(savedParkingRole);
    }

    @Override
    public ParkingRoleDto updateParkingRole(Long userId, Long parkingRoleId, ParkingRoleRequest request) {
        if (!userService.userExistsById(userId)) throw new UserNotFoundException(userId);

        final Long userParkingId = parkingQueryService.getParkingByUserId(userId).parkingId();

        ParkingRole toUpdateParkingRole = parkingRoleRepository
                .findByParkingIdAndParkingRoleId(userParkingId, parkingRoleId)
                .orElseThrow(() -> new ParkingRoleNotFoundException(parkingRoleId));

        if (!request.roleName().equals(toUpdateParkingRole.getRoleName()) &&
                parkingRoleRepository.roleNameExistsInParking(request.roleName(), toUpdateParkingRole.getParkingId()))
            throw new DuplicatedParkingRoleNameException(request.roleName());

        final ParkingRole sourceParkingRole = mapper.fromRequestToModel(request);

        toUpdateParkingRole.updateDetails(sourceParkingRole);

        return mapper.fromModelToDto(parkingRoleRepository.save(toUpdateParkingRole));
    }

    @Override
    public void deleteParkingRole(Long userId, Long parkingRoleId) {
        if (!userService.userExistsById(userId)) throw new UserNotFoundException(userId);
        final Long userParkingId = parkingQueryService.getParkingByUserId(userId).parkingId();

        final ParkingRole toDeleteParkingRole = parkingRoleRepository
                .findByParkingIdAndParkingRoleId(userParkingId, parkingRoleId)
                .orElseThrow(() -> new ParkingRoleNotFoundException(parkingRoleId));

        parkingRoleRepository.delete(toDeleteParkingRole);
    }
}
