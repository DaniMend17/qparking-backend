package com.github.danimend17.qparking.parking.internal.application;

import com.github.danimend17.qparking.parking.ParkingDto;
import com.github.danimend17.qparking.parking.UserParkingService;
import com.github.danimend17.qparking.parking.internal.domain.ParkingNotFoundForUserException;
import com.github.danimend17.qparking.parking.internal.infrastructure.persistence.ParkingRepository;
import com.github.danimend17.qparking.parking.internal.web.mapper.ParkingMapper;
import com.github.danimend17.qparking.shared.identity.CurrentUserProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserParkingServiceImpl implements UserParkingService {

    private final CurrentUserProvider currentUserProvider;
    private final ParkingRepository parkingRepository;
    private final ParkingMapper mapper;

    @Override
    public ParkingDto getUserParking() {
        return parkingRepository.findByUserId(getUserId())
                .map(mapper::toDto)
                .orElseThrow(() -> new ParkingNotFoundForUserException(getUserId()));
    }

    @Override
    public Long getUserParkingId() {
        return parkingRepository.findUserParkingId(getUserId())
                .orElseThrow(() -> new ParkingNotFoundForUserException(getUserId()));
    }

    private Long getUserId() {
        return currentUserProvider.getUserId();
    }
}
