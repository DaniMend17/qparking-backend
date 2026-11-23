package com.github.danimend17.qparking.security;

import java.time.Duration;

public record GeneratedToken(
        String token,
        Duration expiresIn
) {
}
