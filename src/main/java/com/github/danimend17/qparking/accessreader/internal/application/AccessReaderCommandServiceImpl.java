package com.github.danimend17.qparking.accessreader.internal.application;

import com.github.danimend17.qparking.accessreader.AccessReaderDto;
import com.github.danimend17.qparking.accessreader.internal.AccessReaderMapper;
import com.github.danimend17.qparking.accessreader.internal.AccessReaderRepository;
import com.github.danimend17.qparking.accessreader.internal.AccessReaderRequest;
import com.github.danimend17.qparking.accessreader.internal.domain.AccessReader;
import com.github.danimend17.qparking.accessreader.internal.domain.DuplicatedReaderName;
import com.github.danimend17.qparking.accessreader.internal.domain.ReaderNotFoundException;
import com.github.danimend17.qparking.parking.ParkingQueryService;
import com.github.danimend17.qparking.shared.identity.CurrentUserProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AccessReaderCommandServiceImpl implements AccessReaderCommandService {

    private final AccessReaderRepository accessReaderRepository;
    private final AccessReaderMapper mapper;
    private final CurrentUserProvider currentUserProvider;
    private final ParkingQueryService parkingQueryService;

    @Override
    public AccessReaderDto createAccessReader(AccessReaderRequest accessReaderRequest) {
        final Long userParkingId = parkingQueryService.getUserParkingId(currentUserProvider.getUserId());

        if (accessReaderRepository.existsByAccessNameAndParkingId(
                accessReaderRequest.accessName(), userParkingId)) {
            throw new DuplicatedReaderName(accessReaderRequest.accessName());
        }
        final AccessReader accessReader = mapper.toModel(accessReaderRequest);
        accessReader.assignParking(userParkingId);

        return mapper.toDto(accessReaderRepository.save(accessReader));
    }

    @Override
    public AccessReaderDto updateAccessReader(Long accessReaderId, AccessReaderRequest accessReaderRequest) {
        final Long userParkingId = parkingQueryService.getUserParkingId(currentUserProvider.getUserId());

        final AccessReader accessReader = accessReaderRepository
                .findByReaderIdAndParkingId(accessReaderId, userParkingId)
                .orElseThrow(() -> new ReaderNotFoundException(accessReaderId));

        if (!accessReaderRequest.accessName().equals(accessReader.getAccessName()) &&
                accessReaderRepository.existsByAccessNameAndParkingId(accessReaderRequest.accessName(),
                        accessReader.getParkingId())) {
            throw new DuplicatedReaderName(accessReaderRequest.accessName());
        }
        accessReader.updateDetails(mapper.toModel(accessReaderRequest));

        return mapper.toDto(accessReaderRepository.save(accessReader));
    }

    @Override
    public void deleteAccessReader(Long accessReaderId) {
        final Long userParkingId = parkingQueryService.getUserParkingId(currentUserProvider.getUserId());
        final AccessReader accessReader = accessReaderRepository
                .findByReaderIdAndParkingId(accessReaderId, userParkingId)
                .orElseThrow(() -> new ReaderNotFoundException(accessReaderId));

        accessReaderRepository.delete(accessReader);
    }
}
