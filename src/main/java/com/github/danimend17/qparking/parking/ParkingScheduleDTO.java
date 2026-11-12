package com.github.danimend17.qparking.parking;

import com.github.danimend17.qparking.shared.enums.OperationDay;

import java.time.LocalTime;

public record ParkingScheduleDTO(
        OperationDay weekday,
        Boolean isOpen,
        LocalTime openingTime,
        LocalTime closingTime
) {
}
