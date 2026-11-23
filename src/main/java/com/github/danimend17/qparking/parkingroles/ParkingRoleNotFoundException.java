package com.github.danimend17.qparking.parkingroles;

public class ParkingRoleNotFoundException extends RuntimeException {
    public ParkingRoleNotFoundException(Long parkingRoleId) {
        super("Parking role with id: " + parkingRoleId + " does not exist");
    }
}
