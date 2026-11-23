package com.github.danimend17.qparking.parkingroles;

import com.github.danimend17.qparking.shared.enums.CommissionRate;

import java.math.BigDecimal;

public record ParkingRoleDto(
        Long parkingRoleId,
        Long parkingId,
        String roleName,
        CommissionRate commissionPeriod,
        BigDecimal commissionValue
) {
}
