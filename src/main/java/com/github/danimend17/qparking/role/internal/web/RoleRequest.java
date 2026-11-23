package com.github.danimend17.qparking.role.internal.web;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RoleRequest(
        @NotBlank(message = "Role name is mandatory")
        @Size(max = 10, message = "Role name size must be less or equal to 10 characters")
        String name,
        @NotBlank(message = "Description is mandatory")
        @Size(max = 50, message = "Description size must be less or equal to 25 characters")
        String description
) {
}
