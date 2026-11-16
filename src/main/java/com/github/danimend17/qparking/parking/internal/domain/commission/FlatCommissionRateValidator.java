package com.github.danimend17.qparking.parking.internal.domain.commission;

import com.github.danimend17.qparking.parking.internal.domain.Parking;
import com.github.danimend17.qparking.shared.enums.CommissionRate;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
class FlatCommissionRateValidator implements CommissionRateValidator {
    @Override
    public void validate(Parking parking) {
        if (parking.getCommissionRate() == CommissionRate.FLAT &&
                (Objects.isNull(parking.getFlatRate()) || Objects.nonNull(parking.getHourlyRate()))) {
            throw new InvalidCommissionRateStateException(
                    "Flat commission rate type must have only a flat rate value assigned"
            );
        }
    }
}
