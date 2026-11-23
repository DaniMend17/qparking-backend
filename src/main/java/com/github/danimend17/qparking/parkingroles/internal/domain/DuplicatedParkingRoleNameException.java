package com.github.danimend17.qparking.parkingroles.internal.domain;

public class DuplicatedParkingRoleNameException extends RuntimeException {
    public DuplicatedParkingRoleNameException(String roleName) {
        super("Parking role with name: " + roleName + " has already been register in the parking");
    }
}
