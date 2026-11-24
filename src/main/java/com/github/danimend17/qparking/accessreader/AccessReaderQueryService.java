package com.github.danimend17.qparking.accessreader;

import com.github.danimend17.qparking.shared.models.PageResult;

public interface AccessReaderQueryService {

    PageResult<AccessReaderDto> getParkingReaders(int pageNumber);

}
