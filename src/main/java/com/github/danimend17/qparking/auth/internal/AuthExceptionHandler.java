package com.github.danimend17.qparking.auth.internal;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackageClasses = AuthController.class)
public class AuthExceptionHandler {

    @ExceptionHandler({
            AuthenticationException.class,
            UsernameNotFoundException.class,
            DisabledException.class,
            LockedException.class,
            AccountExpiredException.class,
            CredentialsExpiredException.class
    })
    public ProblemDetail authenticationHandler(AuthenticationException e) {
        final ProblemDetail problemDetail =
                ProblemDetail.forStatusAndDetail(HttpStatus.UNAUTHORIZED, "Invalid email or password");
        problemDetail.setTitle("Authentication error");
        return problemDetail;
    }

}
