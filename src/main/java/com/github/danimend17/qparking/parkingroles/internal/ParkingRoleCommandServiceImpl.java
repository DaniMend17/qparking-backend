package com.github.danimend17.qparking.parkingroles.internal;

import com.github.danimend17.qparking.parking.ParkingQueryService;
import com.github.danimend17.qparking.parkingroles.ParkingRoleDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ParkingRoleCommandServiceImpl implements ParkingRoleCommandService {

    private final ParkingRoleRepository parkingRoleRepository;
    private final ParkingQueryService parkingQueryService;

    @Override
    public ParkingRoleDto createParkingRole(ParkingRoleRequest request) {

        return null;
    }

    @Override
    public ParkingRoleDto updateParkingRole(Long parkingRoleId, ParkingRoleRequest request) {
        return null;
    }

    @Override
    public void deleteParkingRole(Long parkingRoleId) {

    }
}
