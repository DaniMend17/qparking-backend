package com.github.danimend17.qparking.parkingroles.internal.application;

import com.github.danimend17.qparking.parking.UserParkingService;
import com.github.danimend17.qparking.parkingroles.ParkingRoleDto;
import com.github.danimend17.qparking.parkingroles.ParkingRoleNotFoundException;
import com.github.danimend17.qparking.parkingroles.ParkingRoleQueryService;
import com.github.danimend17.qparking.parkingroles.internal.web.ParkingRoleMapper;
import com.github.danimend17.qparking.parkingroles.internal.infrastructure.persistence.ParkingRoleRepository;
import com.github.danimend17.qparking.shared.models.PageResult;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ParkingRoleQueryServiceImpl implements ParkingRoleQueryService {

    private final ParkingRoleRepository parkingRoleRepository;
    private final UserParkingService userParkingService;
    private final ParkingRoleMapper mapper;
    private static final int PAGE_SIZE = 5;

    @Override
    public PageResult<ParkingRoleDto> getAllParkingRoles(int pageNumber) {
        final Page<ParkingRoleDto> parkingRolePage = parkingRoleRepository
                .findAll(PageRequest.of(pageNumber, PAGE_SIZE, Sort.by("parkingRoleId").ascending()))
                .map(mapper::fromModelToDto);
        return PageResult.fromPage(parkingRolePage);
    }

    @Override
    public ParkingRoleDto getParkingRoleById(Long parkingRoleId) {
        return parkingRoleRepository.findById(parkingRoleId)
                .map(mapper::fromModelToDto)
                .orElseThrow(() -> new ParkingRoleNotFoundException(parkingRoleId));
    }

    @Override
    public PageResult<ParkingRoleDto> getParkingRolesByParkingId(Long parkingId, int pageNumber) {
        final Page<ParkingRoleDto> parkingRolePage = parkingRoleRepository
                .findAllByParkingId(parkingId,
                        PageRequest.of(pageNumber, PAGE_SIZE, Sort.by("parkingRoleId").ascending()))
                .map(mapper::fromModelToDto);
        return PageResult.fromPage(parkingRolePage);
    }

    @Override
    public PageResult<ParkingRoleDto> getUserParkingRoles(int pageNumber) {
        final Long userParkingId = userParkingService.getUserParkingId();
        final Page<ParkingRoleDto> parkingRoleNamesPage = parkingRoleRepository
                .findAllByParkingId(userParkingId,
                        PageRequest.of(pageNumber, PAGE_SIZE, Sort.by("parkingRoleId").ascending()))
                .map(mapper::fromModelToDto);

        return PageResult.fromPage(parkingRoleNamesPage);
    }

    @Override
    public PageResult<String> getRoleNamesByParkingId(Long parkingId, int pageNumber) {
        final Page<String> parkingRoleNamesPage = parkingRoleRepository
                .findAllRoleNamesByParkingId(parkingId,
                        PageRequest.of(pageNumber, PAGE_SIZE));

        return PageResult.fromPage(parkingRoleNamesPage);
    }

    @Override
    public ParkingRoleDto getParkingRoleByName(Long parkingId, String roleName) {
        return parkingRoleRepository.findByParkingIdAndRoleName(parkingId, roleName)
                .map(mapper::fromModelToDto)
                .orElseThrow();
    }
}
