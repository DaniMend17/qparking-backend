package com.github.danimend17.qparking.auth.internal;

public record AuthResponse(
        String accessToken,
        String tokenType,
        Long expiresIn
) {
}
