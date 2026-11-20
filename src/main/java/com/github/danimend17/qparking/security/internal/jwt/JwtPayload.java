package com.github.danimend17.qparking.security.internal.jwt;

import java.util.Date;
import java.util.List;

public record JwtPayload(
        String subject,
        List<String> roles,
        Date issuedAt,
        Date expiration
) {
}
