package com.github.danimend17.qparking.parkingroles;

public class ParkingRoleNotFoundException extends RuntimeException {
    public ParkingRoleNotFoundException(Long parkingRoleId) {
        super("Parking role with id: " + parkingRoleId + " does not exist in parking");
    }

    public ParkingRoleNotFoundException(String roleName) {
        super("Parking role with name: " + roleName + " does not exist in parking");
    }
}
