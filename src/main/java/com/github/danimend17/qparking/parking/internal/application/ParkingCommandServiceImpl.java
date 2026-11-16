package com.github.danimend17.qparking.parking.internal.application;

import com.github.danimend17.qparking.parking.ParkingDTO;
import com.github.danimend17.qparking.parking.DuplicatedParkingNameException;
import com.github.danimend17.qparking.parking.ParkingLimitExceededException;
import com.github.danimend17.qparking.parking.ParkingNotFoundException;
import com.github.danimend17.qparking.parking.internal.domain.Parking;
import com.github.danimend17.qparking.parking.internal.domain.ParkingSchedule;
import com.github.danimend17.qparking.parking.internal.infrastructure.persistence.ParkingRepository;
import com.github.danimend17.qparking.parking.internal.web.mapper.ParkingMapper;
import com.github.danimend17.qparking.parking.internal.web.mapper.ParkingScheduleMapper;
import com.github.danimend17.qparking.parking.internal.domain.commission.CommissionRateValidator;
import com.github.danimend17.qparking.parking.internal.web.ParkingRequest;
import com.github.danimend17.qparking.user.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ParkingCommandServiceImpl implements ParkingCommandService {

    private final ParkingRepository parkingRepository;
    private final IUserService userService;
    private final ParkingMapper parkingMapper;
    private final ParkingScheduleMapper parkingScheduleMapper;
    private final List<CommissionRateValidator> commissionRateValidators;

    @Override
    public ParkingDTO createParking(ParkingRequest request) {
        userService.getUserById(request.userId());

        if (parkingRepository.existsByParkingName(request.parkingName()))
            throw new DuplicatedParkingNameException(request.parkingName());

        if (parkingRepository.existsByUserId(request.userId()))
            throw new ParkingLimitExceededException(request.userId());

        final Parking parking = parkingMapper.fromRequestToEntity(request);

        commissionRateValidators.forEach(commissionRateValidator ->
                commissionRateValidator.validate(parking));

        final List<ParkingSchedule> parkingSchedule = request.parkingSchedule().stream()
                .map(parkingScheduleMapper::fromRequestToEntity)
                .toList();
        parkingSchedule.forEach(parking::addOrUpdateParkingSchedule);

        final Parking savedParking = parkingRepository.save(parking);

        return parkingMapper.fromEntityToDto(savedParking);
    }

    @Override
    public ParkingDTO updateParking(Long parkingId, ParkingRequest request) {
        if (parkingRepository.existsByParkingName(request.parkingName()))
            throw new DuplicatedParkingNameException(request.parkingName());

        final Parking toUpdateParking = parkingRepository.findParkingWithScheduleById(parkingId)
                .orElseThrow(() -> new ParkingNotFoundException(parkingId));

        final Parking parking = parkingMapper.fromRequestToEntity(request);
        commissionRateValidators.forEach(commissionRateValidator ->
                commissionRateValidator.validate(parking));

        final List<ParkingSchedule> parkingSchedule = request.parkingSchedule().stream()
                .map(parkingScheduleMapper::fromRequestToEntity)
                .toList();
        parkingSchedule.forEach(parking::addOrUpdateParkingSchedule);

        toUpdateParking.updateDetails(parking);

        return parkingMapper.fromEntityToDto(parkingRepository.save(toUpdateParking));
    }

    @Override
    public void deleteParking(Long parkingId) {
        if (!parkingRepository.existsById(parkingId)) throw new ParkingNotFoundException(parkingId);
        parkingRepository.deleteById(parkingId);
    }
}
