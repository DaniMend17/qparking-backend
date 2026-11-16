package com.github.danimend17.qparking.parking.internal.domain.commission;

import com.github.danimend17.qparking.parking.internal.domain.Parking;
import com.github.danimend17.qparking.shared.enums.CommissionRate;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
class MixedCommissionRateValidator implements CommissionRateValidator {
    @Override
    public void validate(Parking parking) {
        if (parking.getCommissionRate() == CommissionRate.MIXED &&
                (Objects.isNull(parking.getHourlyRate()) || Objects.isNull(parking.getFlatRate()))) {
            throw new InvalidCommissionRateStateException(
                    "Mixed commission rate type must have an hourly and flat rate value assigned"
            );
        }
    }
}
