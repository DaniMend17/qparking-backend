package com.github.danimend17.qparking.shared.models;

import org.springframework.data.domain.Page;

public record PageResult<E>(
        Iterable<E> data,
        long totalElements,
        int totalPages,
        int pageNumber
) {

    public static <E> PageResult<E> fromPage(Page<E> page) {
        return new PageResult<>(
                page.getContent(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.getPageable().getPageNumber()
        );
    }

}
