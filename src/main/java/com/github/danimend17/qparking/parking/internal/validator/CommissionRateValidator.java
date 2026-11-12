package com.github.danimend17.qparking.parking.internal.validator;

import com.github.danimend17.qparking.parking.internal.ParkingEntity;

public interface CommissionRateValidator {
    void validate(ParkingEntity parking);
}
