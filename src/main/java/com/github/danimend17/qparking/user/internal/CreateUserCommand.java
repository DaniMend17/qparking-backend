package com.github.danimend17.qparking.user.internal;

import com.github.danimend17.qparking.shared.enums.Platform;

import java.time.LocalDateTime;

public record CreateUserCommand(
        String firstName,
        String lastName,
        String phoneNumber,
        String email,
        String password,
        Platform platform
) {
}
