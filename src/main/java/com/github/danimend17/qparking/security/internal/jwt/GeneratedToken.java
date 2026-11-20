package com.github.danimend17.qparking.security.internal.jwt;

import java.time.Duration;

public record GeneratedToken(
        String token,
        Duration expiresIn
) {
}
