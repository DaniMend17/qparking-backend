package com.github.danimend17.qparking.user;

public class DuplicatedPhoneNumberException extends RuntimeException {
    public DuplicatedPhoneNumberException(String phoneNumber) {
        super("There's already a user registered with phone number: " + phoneNumber);
    }
}
