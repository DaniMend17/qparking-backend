package com.github.danimend17.qparking.parking.internal.domain;

public class DuplicatedParkingNameException extends RuntimeException {
    public DuplicatedParkingNameException(String parkingName) {
        super("A parking with name: " + parkingName + ", has already been register");
    }
}
