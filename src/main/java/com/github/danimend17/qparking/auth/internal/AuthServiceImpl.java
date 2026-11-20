package com.github.danimend17.qparking.auth.internal;

import com.github.danimend17.qparking.security.internal.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    public AuthResponse login(String email, String password) {
        final Authentication authenticatedUser = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(email, password));

        final String accessToken = jwtService.generateToken(authenticatedUser);
        return new AuthResponse(accessToken, "Bearer", jwtService.getTokenExpirationTime());
    }
}
