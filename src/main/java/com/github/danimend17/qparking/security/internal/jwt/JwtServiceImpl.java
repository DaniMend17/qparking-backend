package com.github.danimend17.qparking.security.internal.jwt;

import com.github.danimend17.qparking.security.internal.CustomUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;
import java.util.List;

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
        if (!(authentication.getPrincipal() instanceof CustomUserDetails userDetails)) {
            throw new IllegalArgumentException(
                    "Authentication principal must be CustomUserDetails"
            );
        }
        final Instant now = Instant.now();
        final Instant expiration = now.plusMillis(this.expirationInMillis);

        final List<String> userRoles = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        return Jwts.builder()
                .subject(String.valueOf(userDetails.userId()))
                .issuedAt(Date.from(now))
                .expiration(Date.from(expiration))
                .claim("roles", userRoles)
                .signWith(this.signingKey, Jwts.SIG.HS256)
                .compact();
    }

    @Override
    public Claims extractClaims(String token) {
        return Jwts.parser()
                .verifyWith(signingKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    @Override
    public String extractSubject(String token) {
        return extractClaims(token).getSubject();
    }

    @Override
    public Date getExpirationDate(String token) {
        return extractClaims(token).getExpiration();
    }

    @Override
    public boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiration().before(Date.from(Instant.now()));
    }

    private SecretKey setSigningKey(String secret) {
        final byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
