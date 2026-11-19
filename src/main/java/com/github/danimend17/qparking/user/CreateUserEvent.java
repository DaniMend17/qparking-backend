package com.github.danimend17.qparking.user;

public record CreateUserEvent(
        Long userId,
        String phoneNumber
) {
}
