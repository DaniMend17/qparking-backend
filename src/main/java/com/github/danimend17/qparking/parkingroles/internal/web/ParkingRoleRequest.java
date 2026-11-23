package com.github.danimend17.qparking.parkingroles.internal.web;

import com.github.danimend17.qparking.shared.enums.CommissionRate;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record ParkingRoleRequest(
        @NotBlank(message = "Role name value is mandatory")
        @Pattern(regexp = "^[a-zA-Z0-9 ]*$", message = "Role name contains invalid characters")
        @Size(min = 4, max = 50, message = "Role name length must be between 4 and 50 characters")
        String roleName,

        @NotNull(message = "Commission value is mandatory")
        @DecimalMin(value = "0", message = "Commission value must be equal or greater than 0")
        @DecimalMax(value = "999.99", message = "Commission value must be equal or less than 999.99")
        @Digits(integer = 3, fraction = 2, message = "Commission value can only have 3 integer digits and 2 decimals")
        BigDecimal commissionValue,

        @NotNull(message = "Commission period value is mandatory")
        CommissionRate commissionPeriod
) {
}
