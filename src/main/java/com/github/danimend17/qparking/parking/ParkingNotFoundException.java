package com.github.danimend17.qparking.parking;

public class ParkingNotFoundException extends RuntimeException {

    public ParkingNotFoundException() {
        super("Given parking does not exit");
    }

    public ParkingNotFoundException(Long parkingId) {
        super("Parking with id: " + parkingId + " does not exit");
    }

}
