package com.github.danimend17.qparking.parking.internal.application;

import com.github.danimend17.qparking.parking.ParkingDTO;
import com.github.danimend17.qparking.parking.internal.domain.*;
import com.github.danimend17.qparking.parking.internal.infrastructure.persistence.ParkingRepository;
import com.github.danimend17.qparking.parking.internal.web.mapper.ParkingMapper;
import com.github.danimend17.qparking.parking.internal.domain.commission.CommissionRateValidator;
import com.github.danimend17.qparking.parking.internal.web.ParkingRequest;
import com.github.danimend17.qparking.shared.identity.CurrentUserProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ParkingCommandServiceImpl implements ParkingCommandService {

    private final ParkingRepository parkingRepository;
    private final ParkingMapper parkingMapper;
    private final CurrentUserProvider currentUser;
    private final List<CommissionRateValidator> commissionRateValidators;

    @Override
    public ParkingDTO createParking(ParkingRequest request) {
        if (parkingRepository.existsByParkingName(request.parkingName()))
            throw new DuplicatedParkingNameException(request.parkingName());

        if (parkingRepository.existsByUserId(currentUser.getUserId()))
            throw new ParkingLimitExceededException(currentUser.getUserId());

        final Parking parking = parkingMapper.fromRequestToEntity(request);
        parking.assignUser(currentUser.getUserId());

        commissionRateValidators.forEach(commissionRateValidator ->
                commissionRateValidator.validate(parking));

        final Parking savedParking = parkingRepository.save(parking);

        return parkingMapper.fromEntityToDto(savedParking);
    }

    @Override
    public ParkingDTO updateParking(ParkingRequest request) {
        final Parking toUpdateParking = parkingRepository.findUserParking(currentUser.getUserId())
                .orElseThrow(() -> new ParkingNotFoundForUserException(currentUser.getUserId()));

        if (!request.parkingName().equals(toUpdateParking.getParkingName()) &&
                parkingRepository.existsByParkingName(request.parkingName())) {
            throw new DuplicatedParkingNameException(request.parkingName());
        }
        final Parking parking = parkingMapper.fromRequestToEntity(request);

        commissionRateValidators.forEach(commissionRateValidator ->
                commissionRateValidator.validate(parking));

        toUpdateParking.updateDetails(parking);
        return parkingMapper.fromEntityToDto(parkingRepository.save(toUpdateParking));
    }

    @Override
    public void deleteParking() {
        final Parking toDeleteParking = parkingRepository.findUserParking(currentUser.getUserId())
                .orElseThrow(() -> new ParkingNotFoundForUserException(currentUser.getUserId()));

        parkingRepository.delete(toDeleteParking);
    }
}
