package com.github.danimend17.qparking.user.internal;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.danimend17.qparking.shared.enums.Platform;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record UserResponse(
        Long userId,
        String firstName,
        String lastName,
        String phoneNumber,
        String email,
        BigDecimal credit,
        Platform platform,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime createdAt
) {
}
