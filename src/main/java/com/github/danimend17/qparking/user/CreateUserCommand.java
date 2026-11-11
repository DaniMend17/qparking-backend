package com.github.danimend17.qparking.user;

import com.github.danimend17.qparking.shared.enums.Platform;

public record CreateUserCommand(
        String firstName,
        String lastName,
        String phoneNumber,
        String email,
        String password,
        Platform platform
) {
}
