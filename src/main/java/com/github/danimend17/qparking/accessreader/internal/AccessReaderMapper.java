package com.github.danimend17.qparking.accessreader.internal;

import com.github.danimend17.qparking.accessreader.AccessReaderDto;
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
