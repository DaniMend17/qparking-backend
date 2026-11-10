package com.github.danimend17.qparking.user.internal;

import com.github.danimend17.qparking.shared.enums.Platform;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateUserRequest(
        @NotBlank(message = "First name value is mandatory")
        String firstName,
        @NotBlank(message = "Last name value is mandatory")
        String lastName,
        @NotBlank(message = "Phone number is mandatory")
        @Size(min = 10, max = 10, message = "Phone number length must be 10 numbers")
        String phoneNumber,
        @NotBlank(message = "Email is mandatory")
        @Email(message = "Email format is invalid")
        String email,
        @NotBlank(message = "Password is mandatory")
        @Size(min = 8, message = "Password size must be at least 8 characters long")
        String password,
        @NotNull(message = "Platform value is mandatory")
        Platform platform
) {
}
