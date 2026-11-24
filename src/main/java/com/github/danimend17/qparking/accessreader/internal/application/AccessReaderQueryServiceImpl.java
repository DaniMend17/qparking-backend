package com.github.danimend17.qparking.accessreader.internal.application;

import com.github.danimend17.qparking.accessreader.AccessReaderDto;
import com.github.danimend17.qparking.accessreader.AccessReaderQueryService;
import com.github.danimend17.qparking.accessreader.internal.AccessReaderMapper;
import com.github.danimend17.qparking.accessreader.internal.infrastructure.persistence.AccessReaderRepository;
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
public class AccessReaderQueryServiceImpl implements AccessReaderQueryService {

    private final AccessReaderRepository accessReaderRepository;
    private final AccessReaderMapper mapper;
    private static final int PAGE_SIZE = 10;

    @Override
    public PageResult<AccessReaderDto> getParkingReaders(Long parkingId, int pageNumber) {
        final Page<AccessReaderDto> accessReaderPage = accessReaderRepository.findAllByParkingId(parkingId,
                        PageRequest.of(pageNumber, PAGE_SIZE, Sort.by("readerId")))
                .map(mapper::toDto);

        return PageResult.fromPage(accessReaderPage);
    }

}
