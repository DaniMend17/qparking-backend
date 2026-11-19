package com.github.danimend17.qparking.user;

public record UserAuthenticationDetails(
        Long userId,
        String email,
        String password
) {
}
