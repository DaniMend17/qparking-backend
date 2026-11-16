package com.github.danimend17.qparking.parking;

public class ParkingNotFoundException extends RuntimeException {
    public ParkingNotFoundException(Long parkId) {
        super("Parking with id: " + parkId + " does not exit");
    }
}
