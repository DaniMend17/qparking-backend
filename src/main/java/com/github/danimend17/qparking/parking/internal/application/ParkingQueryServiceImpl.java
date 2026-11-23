package com.github.danimend17.qparking.parking.internal.application;

import com.github.danimend17.qparking.parking.ParkingDto;
import com.github.danimend17.qparking.parking.ParkingQueryService;
import com.github.danimend17.qparking.parking.ParkingNotFoundException;
import com.github.danimend17.qparking.parking.internal.domain.ParkingNotFoundForUserException;
import com.github.danimend17.qparking.parking.internal.infrastructure.persistence.ParkingRepository;
import com.github.danimend17.qparking.parking.internal.web.mapper.ParkingMapper;
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
public class ParkingQueryServiceImpl implements ParkingQueryService {

    private final ParkingRepository parkingRepository;
    private final ParkingMapper mapper;
    private static final int PAGE_SIZE = 3;


    @Override
    public PageResult<ParkingDto> getParkings(int pageNumber) {
        Page<ParkingDto> parkingPage = parkingRepository.findAll(PageRequest.of(pageNumber,
                        PAGE_SIZE, Sort.by("parkingId").ascending()))
                .map(mapper::toDto);
        return PageResult.fromPage(parkingPage, pageNumber);
    }

    @Override
    public ParkingDto getParkingById(Long parkingId) {
        return parkingRepository.findById(parkingId)
                .map(mapper::toDto)
                .orElseThrow(() -> new ParkingNotFoundException(parkingId));
    }

    @Override
    public boolean userHasAlreadyRegisterAParking(Long userId) {
        return parkingRepository.existsByUserId(userId);
    }

    @Override
    public boolean parkingExistsById(Long parkingId) {
        return parkingRepository.existsById(parkingId);
    }

    @Override
    public ParkingDto getParkingByUserId(Long userId) {
        return parkingRepository.findByUserId(userId)
                .map(mapper::toDto)
                .orElseThrow(() -> new ParkingNotFoundForUserException(userId));
    }
}
