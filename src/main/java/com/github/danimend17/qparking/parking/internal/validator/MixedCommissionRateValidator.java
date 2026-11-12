package com.github.danimend17.qparking.parking.internal.validator;

import com.github.danimend17.qparking.parking.internal.ParkingEntity;
import com.github.danimend17.qparking.shared.enums.CommissionRate;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
class MixedCommissionRateValidator implements CommissionRateValidator {
    @Override
    public void validate(ParkingEntity parking) {
        if (parking.getCommissionRate() == CommissionRate.MIXED &&
                (Objects.isNull(parking.getHourlyRate()) || Objects.isNull(parking.getFlatRate()))) {
            throw new IllegalStateException(
                    "Mixed commission rate type must have an hourly and flat rate value assigned"
            );
        }
    }
}
