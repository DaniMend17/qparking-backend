package com.github.danimend17.qparking.parking.internal.application;

import com.github.danimend17.qparking.parking.ParkingQueryService;
import com.github.danimend17.qparking.parking.ParkingDTO;
import com.github.danimend17.qparking.parking.ParkingNotFoundException;
import com.github.danimend17.qparking.parking.internal.infrastructure.persistence.ParkingRepository;
import com.github.danimend17.qparking.parking.internal.web.mapper.ParkingMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    public Page<ParkingDTO> getParkings(int pageNumber) {
        return parkingRepository.findAll(PageRequest.of(pageNumber, PAGE_SIZE))
                .map(mapper::fromEntityToDto);
    }

    @Override
    public ParkingDTO getParkingById(Long parkingId) {
        return parkingRepository.findById(parkingId)
                .map(mapper::fromEntityToDto)
                .orElseThrow(() -> new ParkingNotFoundException(parkingId));
    }

    @Override
    public boolean userHasAlreadyRegisterAParking(Long userId) {
        return parkingRepository.existsByUserId(userId);
    }

    @Override
    public boolean existsById(Long parkingId) {
        return parkingRepository.existsById(parkingId);
    }
}
