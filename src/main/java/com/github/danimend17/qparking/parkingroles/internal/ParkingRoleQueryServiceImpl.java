package com.github.danimend17.qparking.parkingroles.internal;

import com.github.danimend17.qparking.parkingroles.ParkingRoleDto;
import com.github.danimend17.qparking.parkingroles.ParkingRoleNotFoundException;
import com.github.danimend17.qparking.parkingroles.ParkingRoleQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ParkingRoleQueryServiceImpl implements ParkingRoleQueryService {

    private final ParkingRoleRepository parkingRoleRepository;
    private final ParkingRoleMapper mapper;
    private static final int PAGE_SIZE = 5;

    @Override
    public Page<ParkingRoleDto> getAllParkingRoles(int pageNumber) {
        return parkingRoleRepository.findAll(PageRequest.of(pageNumber, PAGE_SIZE))
                .map(mapper::fromModelToDto);
    }

    @Override
    public ParkingRoleDto getParkingRoleById(Long parkingRoleId) {
        return parkingRoleRepository.findById(parkingRoleId)
                .map(mapper::fromModelToDto)
                .orElseThrow(() -> new ParkingRoleNotFoundException(parkingRoleId));
    }

    @Override
    public Page<ParkingRoleDto> getParkingRolesOfParkingWithId(Long parkingId, int pageNumber) {
        return parkingRoleRepository.findAllByParkingId(parkingId, PageRequest.of(pageNumber, PAGE_SIZE))
                .map(mapper::fromModelToDto);
    }

    @Override
    public Page<String> getParkingRoleNamesOfParkingWithId(Long parkingId, int pageNumber) {
        return parkingRoleRepository.findAllRoleNamesByParkingId(parkingId, PageRequest.of(pageNumber, PAGE_SIZE));
    }
}
