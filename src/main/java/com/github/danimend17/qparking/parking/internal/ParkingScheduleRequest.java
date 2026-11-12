package com.github.danimend17.qparking.parking.internal;

import com.github.danimend17.qparking.shared.enums.OperationDay;
import jakarta.validation.constraints.NotNull;

import java.time.LocalTime;

public record ParkingScheduleRequest(
        @NotNull(message = "Weekday value is mandatory")
        OperationDay weekday,
        @NotNull(message = "Is open value is mandatory")
        Boolean isOpen,
        @NotNull(message = "Opening time value is mandatory")
        LocalTime openingTime,
        @NotNull(message = "Closing time value is mandatory")
        LocalTime closingTime
) {
}
