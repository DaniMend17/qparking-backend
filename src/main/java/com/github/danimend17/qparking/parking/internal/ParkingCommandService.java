package com.github.danimend17.qparking.parking.internal;

import com.github.danimend17.qparking.parking.*;
import com.github.danimend17.qparking.parking.internal.mapper.ParkingMapper;
import com.github.danimend17.qparking.parking.internal.mapper.ParkingScheduleMapper;
import com.github.danimend17.qparking.parking.internal.validator.CommissionRateValidator;
import com.github.danimend17.qparking.user.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ParkingCommandService implements IParkingCommandService {

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

        final ParkingEntity parkingEntity = parkingMapper.fromRequestToEntity(request);

        commissionRateValidators.forEach(commissionRateValidator ->
                commissionRateValidator.validate(parkingEntity));

        final List<ParkingScheduleEntity> parkingSchedule = request.parkingSchedule().stream()
                .map(parkingScheduleMapper::fromRequestToEntity)
                .toList();
        parkingSchedule.forEach(parkingEntity::addOrUpdateParkingSchedule);

        final ParkingEntity savedParking = parkingRepository.save(parkingEntity);

        return parkingMapper.fromEntityToDto(savedParking);
    }

    @Override
    public ParkingDTO updateParking(Long parkingId, ParkingRequest request) {
        if (parkingRepository.existsByParkingName(request.parkingName()))
            throw new DuplicatedParkingNameException(request.parkingName());

        final ParkingEntity toUpdateParkingEntity = parkingRepository.findParkingWithScheduleById(parkingId)
                .orElseThrow(() -> new ParkingNotFoundException(parkingId));

        final ParkingEntity parkingEntity = parkingMapper.fromRequestToEntity(request);
        commissionRateValidators.forEach(commissionRateValidator ->
                commissionRateValidator.validate(parkingEntity));

        final List<ParkingScheduleEntity> parkingSchedule = request.parkingSchedule().stream()
                .map(parkingScheduleMapper::fromRequestToEntity)
                .toList();
        parkingSchedule.forEach(parkingEntity::addOrUpdateParkingSchedule);

        toUpdateParkingEntity.updateDetails(parkingEntity);

        return parkingMapper.fromEntityToDto(parkingRepository.save(toUpdateParkingEntity));
    }

    @Override
    public void deleteParking(Long parkingId) {
        if (!parkingRepository.existsById(parkingId)) throw new ParkingNotFoundException(parkingId);
        parkingRepository.deleteById(parkingId);
    }
}
