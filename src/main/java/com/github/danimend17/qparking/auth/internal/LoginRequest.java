package com.github.danimend17.qparking.auth.internal;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginRequest(
        @NotBlank(message = "Email is mandatory")
        @Email(message = "Email format is invalid")
        String email,
        @NotBlank(message = "Password is mandatory")
        @Size(min = 8, message = "Password size must be at least 8 characters long")
        String password
) {
}
