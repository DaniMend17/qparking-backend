package com.github.danimend17.qparking.security.internal.jwt;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;

public interface JwtService {
    String generateToken(Authentication authentication);

    Claims extractClaims(String token);

    String extractUserName(String token);

    Date getExpirationDate(String token);

    boolean isTokenExpired(String token);

    boolean isTokenValid(String token, UserDetails userDetails);
}
