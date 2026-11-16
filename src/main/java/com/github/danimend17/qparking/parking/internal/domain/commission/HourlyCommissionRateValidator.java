package com.github.danimend17.qparking.parking.internal.domain.commission;


import com.github.danimend17.qparking.parking.internal.domain.Parking;
import com.github.danimend17.qparking.shared.enums.CommissionRate;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
class HourlyCommissionRateValidator implements CommissionRateValidator {
    @Override
    public void validate(Parking parking) {
        if (parking.getCommissionRate() == CommissionRate.HOURLY &&
                (Objects.isNull(parking.getHourlyRate()) || Objects.nonNull(parking.getFlatRate()))) {
            throw new InvalidCommissionRateStateException(
                    "Hourly commission rate type must have only a hourly rate value assigned"
            );
        }
    }
}
