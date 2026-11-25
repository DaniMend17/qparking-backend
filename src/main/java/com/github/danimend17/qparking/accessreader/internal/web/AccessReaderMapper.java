package com.github.danimend17.qparking.accessreader.internal.web;

import com.github.danimend17.qparking.accessreader.AccessReaderDto;
import com.github.danimend17.qparking.accessreader.internal.domain.AccessReader;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AccessReaderMapper {

    @Mapping(target = "readerId", ignore = true)
    @Mapping(target = "parkingId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    AccessReader toModel(AccessReaderRequest request);

    AccessReaderDto toDto(AccessReader accessReader);

}
