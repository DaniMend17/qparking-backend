package com.github.danimend17.qparking.security.internal.jwt;

import com.github.danimend17.qparking.security.internal.CustomUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Duration;
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
        this.signingKey = buildSigningKey(secret);
        this.expirationInMillis = expirationInMillis;
    }

    @Override
    public GeneratedToken generateToken(Authentication authentication) {
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

        final String jwtToken = Jwts.builder()
                .subject(String.valueOf(userDetails.userId()))
                .issuedAt(Date.from(now))
                .expiration(Date.from(expiration))
                .claim("roles", userRoles)
                .signWith(this.signingKey, Jwts.SIG.HS256)
                .compact();
        return new GeneratedToken(jwtToken, Duration.ofMillis(expirationInMillis));
    }

    @Override
    public JwtPayload validateToken(String token) {
        final Claims claims = Jwts.parser()
                .verifyWith(signingKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return new JwtPayload(
                claims.getSubject(),
                claims.get("roles", List.class),
                claims.getIssuedAt(),
                claims.getExpiration()
        );
    }

    private SecretKey buildSigningKey(String secret) {
        final byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
