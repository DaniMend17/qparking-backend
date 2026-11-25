package com.github.danimend17.qparking.parking;

public class ParkingNotFoundForUserException extends RuntimeException {
    public ParkingNotFoundForUserException(Long userId) {
        super("There is no parking associated for user with id: " + userId);
    }
}
