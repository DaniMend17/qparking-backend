package com.github.danimend17.qparking.user;

import com.github.danimend17.qparking.shared.enums.Platform;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record UserDTO(
        Long userId,
        String firstName,
        String lastName,
        String phoneNumber,
        String email,
        BigDecimal credit,
        Platform platform,
        LocalDateTime createdAt
) {
}
