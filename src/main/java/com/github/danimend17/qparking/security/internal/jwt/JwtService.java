package com.github.danimend17.qparking.security.internal.jwt;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.Authentication;

import java.util.Date;

public interface JwtService {
    String generateToken(Authentication authentication);

    Claims extractClaims(String token);

    String extractSubject(String token);

    Date getExpirationDate(String token);

    boolean isTokenExpired(String token);
}
