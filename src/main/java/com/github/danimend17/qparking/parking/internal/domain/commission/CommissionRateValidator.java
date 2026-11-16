package com.github.danimend17.qparking.parking.internal.domain.commission;

import com.github.danimend17.qparking.parking.internal.domain.Parking;

public interface CommissionRateValidator {
    void validate(Parking parking);
}
