package com.github.danimend17.qparking.user;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long userId) {
        super("User with id: " + userId + " not founded");
    }

    public UserNotFoundException(String email) {
        super("User with email: " + email + " not founded");
    }

    public UserNotFoundException() {
        super("User does not exist");
    }
}
