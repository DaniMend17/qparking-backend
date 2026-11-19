package com.github.danimend17.qparking.security.internal.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;

@Service
public class JwtServiceImpl implements JwtService {

    private final SecretKey signingKey;
    private final long expirationInMillis;

    public JwtServiceImpl(
            @Value("${security.jwt.secret}") String secret,
            @Value("${security.jwt.expiration-ms}") long expirationInMillis
    ) {
        this.signingKey = setSigningKey(secret);
        this.expirationInMillis = expirationInMillis;
    }

    @Override
    public String generateToken(Authentication authentication) {
        final Instant now = Instant.now();
        final Instant expiration = now.plusMillis(this.expirationInMillis);
        return Jwts.builder()
                .subject(authentication.getName())
                .issuedAt(Date.from(now))
                .expiration(Date.from(expiration))
                .signWith(this.signingKey, Jwts.SIG.HS256)
                .compact();
    }

    @Override
    public Claims extractClaims(String token) {
        return null;
    }

    @Override
    public String extractUserName(String token) {
        return "";
    }

    @Override
    public Date getExpirationDate(String token) {
        return null;
    }

    @Override
    public boolean isTokenExpired(String token) {
        return false;
    }

    @Override
    public boolean isTokenValid(String token, UserDetails userDetails) {
        return false;
    }

    private SecretKey setSigningKey(String secret) {
        final byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
