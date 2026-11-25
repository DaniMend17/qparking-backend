package com.github.danimend17.qparking.accessreader;

public record AccessReaderDto(
        Long readerId,
        Long parkingId,
        String accessName,
        Boolean isEntry,
        Boolean isActive
) {
}
