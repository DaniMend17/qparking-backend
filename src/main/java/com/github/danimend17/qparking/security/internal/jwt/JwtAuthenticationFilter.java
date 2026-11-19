package com.github.danimend17.qparking.security.internal.jwt;

import com.github.danimend17.qparking.user.UserAuthenticationService;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        final String authorization = request.getHeader("Authorization");
        if (Objects.isNull(authorization) || !authorization.startsWith("Bearer")) {
            filterChain.doFilter(request, response);
            return;
        }
        final String authToken = authorization.substring(7);

        try {
            final JwtPayload jwtPayload = jwtService.validateToken(authToken);

            userDetailsService.loadUserById(Long.parseLong(jwtPayload.subject()));


            if (Objects.isNull(SecurityContextHolder.getContext().getAuthentication())) {

                final SecurityContext securityContext = SecurityContextHolder.createEmptyContext();


//                final UsernamePasswordAuthenticationToken authentication =
//                        new UsernamePasswordAuthenticationToken();
            }


        } catch (JwtException e) {

        }

        filterChain.doFilter(request, response);
    }
}
