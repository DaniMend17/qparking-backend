package com.github.danimend17.qparking.parking.internal.web;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Objects;

public class DifferentOpeningAndClosingTimeValidator implements
        ConstraintValidator<DifferentOpeningAndClosingTime, ParkingScheduleRequest> {

    @Override
    public boolean isValid(ParkingScheduleRequest parkingScheduleRequest, ConstraintValidatorContext context) {
        if (Objects.isNull(parkingScheduleRequest.weekday()) || Objects.isNull(parkingScheduleRequest.isOpen())) {
            return true;
        }
        return !parkingScheduleRequest.openingTime().equals(parkingScheduleRequest.closingTime());
    }
}
