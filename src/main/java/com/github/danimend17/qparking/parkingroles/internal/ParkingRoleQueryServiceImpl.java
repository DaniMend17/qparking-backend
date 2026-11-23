package com.github.danimend17.qparking.parkingroles.internal;

import com.github.danimend17.qparking.parking.ParkingQueryService;
import com.github.danimend17.qparking.parkingroles.ParkingRoleDto;
import com.github.danimend17.qparking.parkingroles.ParkingRoleNotFoundException;
import com.github.danimend17.qparking.parkingroles.ParkingRoleQueryService;
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
    private final ParkingQueryService parkingQueryService;
    private final ParkingRoleMapper mapper;
    private static final int PAGE_SIZE = 5;

    @Override
    public PageResult<ParkingRoleDto> getAllParkingRoles(int pageNumber) {
        final Page<ParkingRoleDto> parkingRolePage = parkingRoleRepository
                .findAll(PageRequest.of(pageNumber, PAGE_SIZE, Sort.by("parkingRoleId").ascending()))
                .map(mapper::fromModelToDto);
        return PageResult.fromPage(parkingRolePage, pageNumber);
    }

    @Override
    public ParkingRoleDto getParkingRoleById(Long parkingRoleId) {
        return parkingRoleRepository.findById(parkingRoleId)
                .map(mapper::fromModelToDto)
                .orElseThrow(() -> new ParkingRoleNotFoundException(parkingRoleId));
    }

    @Override
    public PageResult<ParkingRoleDto> getParkingRolesOfParking(Long parkingId, int pageNumber) {
        final Page<ParkingRoleDto> parkingRolePage = parkingRoleRepository
                .findAllByParkingId(parkingId,
                        PageRequest.of(pageNumber, PAGE_SIZE, Sort.by("parkingRoleId").ascending()))
                .map(mapper::fromModelToDto);
        return PageResult.fromPage(parkingRolePage, pageNumber);
    }

    @Override
    public PageResult<ParkingRoleDto> getUserParkingRoles(Long userId, int pageNumber) {
        final Long userParkingId = parkingQueryService.getParkingByUserId(userId).parkingId();
        final Page<ParkingRoleDto> parkingRoleNamesPage = parkingRoleRepository
                .findAllByParkingId(userParkingId,
                        PageRequest.of(pageNumber, PAGE_SIZE, Sort.by("parkingRoleId").ascending()))
                .map(mapper::fromModelToDto);

        return PageResult.fromPage(parkingRoleNamesPage, pageNumber);
    }

    @Override
    public PageResult<String> getParkingRoleNamesOfParking(Long parkingId, int pageNumber) {
        final Page<String> parkingRoleNamesPage = parkingRoleRepository
                .findAllRoleNamesByParkingId(parkingId,
                        PageRequest.of(pageNumber, PAGE_SIZE));

        return PageResult.fromPage(parkingRoleNamesPage, pageNumber);
    }
}
