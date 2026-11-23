package com.github.danimend17.qparking.parking;

import com.github.danimend17.qparking.shared.enums.CommissionRate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record ParkingDto(
        Long parkingId,
        Long userId,
        String parkingName,
        CommissionRate commissionRate,
        BigDecimal hourlyRate,
        BigDecimal flatRate,
        Double latitude,
        Double longitude,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        List<ParkingScheduleDto> parkingSchedule
) {
}
