package com.github.danimend17.qparking.parkingroles.internal.domain;

public class InvalidParkingRoleAccessException extends RuntimeException {
    public InvalidParkingRoleAccessException(Long userId) {
        super("User with id " + userId + " can not access the parking role");
    }
}
