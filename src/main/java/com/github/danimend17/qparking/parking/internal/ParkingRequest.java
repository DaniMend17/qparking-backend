package com.github.danimend17.qparking.parking.internal;

import com.github.danimend17.qparking.shared.enums.CommissionRate;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.List;

public record ParkingRequest(
        @NotNull(message = "User id value is mandatory")
        Long userId,

        @NotBlank(message = "Parking name is mandatory")
        @Size(min = 10, max = 120,
                message = "Parking name must have a min length of 10 characters and a max length of 120 characters")
        String parkingName,

        @NotNull(message = "Commission rate type is mandatory")
        CommissionRate commissionRate,

        @DecimalMin(value = "1", message = "Hourly rate value must be greater or equal to 1")
        @DecimalMax(value = "999.99", message = "Hourly rate value must be less or equal to 999.99")
        BigDecimal hourlyRate,

        @DecimalMin(value = "1", message = "Flat rate value must be greater or equal to 1")
        @DecimalMax(value = "999.99", message = "Flat rate rate value must be less or equal to 999.99")
        BigDecimal flatRate,

        @NotNull(message = "Latitude value is mandatory")
        Double latitude,

        @NotNull(message = "Longitude value is mandatory")
        Double longitude,

        @NotNull(message = "Parking schedule is mandatory")
        @Valid
        List<ParkingScheduleRequest> parkingSchedule
) {
}
