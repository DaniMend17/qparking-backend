package com.github.danimend17.qparking.user.internal;

public record CreateUserEvent(
        Long userId,
        String phoneNumber
) {
}
