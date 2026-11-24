package com.github.danimend17.qparking.accessreader.internal;

public interface AccessReaderCommandService {

    AccessReaderDto createAccessReader(AccessReaderRequest accessReaderRequest);

    AccessReaderDto updateAccessReader(Long accessReaderId, AccessReaderRequest accessReaderRequest);

    void deleteAccessReader(Long accessReaderId);

}
