package com.github.danimend17.qparking.accessreader.internal.application;

import com.github.danimend17.qparking.accessreader.AccessReaderDto;
import com.github.danimend17.qparking.accessreader.internal.web.AccessReaderMapper;
import com.github.danimend17.qparking.accessreader.internal.infrastructure.persistence.AccessReaderRepository;
import com.github.danimend17.qparking.accessreader.internal.web.AccessReaderRequest;
import com.github.danimend17.qparking.accessreader.internal.domain.AccessReader;
import com.github.danimend17.qparking.accessreader.internal.domain.DuplicatedReaderNameException;
import com.github.danimend17.qparking.accessreader.internal.domain.ReaderNotFoundException;
import com.github.danimend17.qparking.parking.UserParkingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AccessReaderCommandServiceImpl implements AccessReaderCommandService {

    private final AccessReaderRepository accessReaderRepository;
    private final AccessReaderMapper mapper;
    private final UserParkingService userParkingService;

    @Override
    public AccessReaderDto createAccessReader(AccessReaderRequest accessReaderRequest) {
        final Long userParkingId = userParkingService.getUserParkingId();

        if (accessReaderRepository.existsByAccessNameAndParkingId(
                accessReaderRequest.accessName(), userParkingId)) {
            throw new DuplicatedReaderNameException(accessReaderRequest.accessName());
        }
        final AccessReader accessReader = mapper.toModel(accessReaderRequest);
        accessReader.assignParking(userParkingId);

        return mapper.toDto(accessReaderRepository.save(accessReader));
    }

    @Override
    public AccessReaderDto updateAccessReader(Long accessReaderId, AccessReaderRequest accessReaderRequest) {
        final Long userParkingId = userParkingService.getUserParkingId();

        final AccessReader accessReader = accessReaderRepository
                .findByReaderIdAndParkingId(accessReaderId, userParkingId)
                .orElseThrow(() -> new ReaderNotFoundException(accessReaderId));

        if (!accessReaderRequest.accessName().equals(accessReader.getAccessName()) &&
                accessReaderRepository.existsByAccessNameAndParkingId(accessReaderRequest.accessName(),
                        accessReader.getParkingId())) {
            throw new DuplicatedReaderNameException(accessReaderRequest.accessName());
        }
        accessReader.updateDetails(mapper.toModel(accessReaderRequest));

        return mapper.toDto(accessReaderRepository.save(accessReader));
    }

    @Override
    public void deleteAccessReader(Long accessReaderId) {
        final Long userParkingId = userParkingService.getUserParkingId();
        final AccessReader accessReader = accessReaderRepository
                .findByReaderIdAndParkingId(accessReaderId, userParkingId)
                .orElseThrow(() -> new ReaderNotFoundException(accessReaderId));

        accessReaderRepository.delete(accessReader);
    }
}
