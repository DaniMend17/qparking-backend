package com.github.danimend17.qparking.accessreader.internal;

import com.github.danimend17.qparking.accessreader.AccessReaderDto;

public interface AccessReaderCommandService {

    AccessReaderDto createAccessReader(AccessReaderRequest accessReaderRequest);

    AccessReaderDto updateAccessReader(Long accessReaderId, AccessReaderRequest accessReaderRequest);

    void deleteAccessReader(Long accessReaderId);

}
