package com.github.danimend17.qparking.user.internal.domain;

public class DuplicatedEmailException extends RuntimeException {
    public DuplicatedEmailException(String email) {
        super("Email: " + " is already register");
    }
}
