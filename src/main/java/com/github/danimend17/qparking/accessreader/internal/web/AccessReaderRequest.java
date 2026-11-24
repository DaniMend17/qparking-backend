package com.github.danimend17.qparking.accessreader.internal.web;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AccessReaderRequest(
        @NotBlank(message = "Access name is mandatory")
        @Size(min = 1, max = 75, message = "Access name length must be between 1 and 75 characters")
        String accessName,

        @NotNull(message = "Entry value is mandatory")
        Boolean isEntry,

        Boolean isActive
) {
}
