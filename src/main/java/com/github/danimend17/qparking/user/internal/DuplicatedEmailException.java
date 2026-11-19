package com.github.danimend17.qparking.user.internal;

public class DuplicatedEmailException extends RuntimeException {
    public DuplicatedEmailException(String email) {
        super("Email: " + " is already register");
    }
}
