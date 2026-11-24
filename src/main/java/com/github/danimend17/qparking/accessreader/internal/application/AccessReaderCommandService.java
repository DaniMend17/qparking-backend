package com.github.danimend17.qparking.accessreader.internal.application;

import com.github.danimend17.qparking.accessreader.AccessReaderDto;
import com.github.danimend17.qparking.accessreader.internal.web.AccessReaderRequest;

public interface AccessReaderCommandService {

    AccessReaderDto createAccessReader(AccessReaderRequest accessReaderRequest);

    AccessReaderDto updateAccessReader(Long accessReaderId, AccessReaderRequest accessReaderRequest);

    void deleteAccessReader(Long accessReaderId);

}
