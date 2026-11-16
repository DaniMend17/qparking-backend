package com.github.danimend17.qparking.parking.internal.domain;

public class ParkingLimitExceededException extends RuntimeException {
    public ParkingLimitExceededException(Long userId) {
        super("User with id: " + userId + " has already register a parking");
    }
}
