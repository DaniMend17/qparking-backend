package com.github.danimend17.qparking.security.internal.jwt;

import org.springframework.security.core.Authentication;

public interface JwtService {
    GeneratedToken generateToken(Authentication authentication);

    JwtPayload validateToken(String token);

}
