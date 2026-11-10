package com.github.danimend17.qparking.user.internal;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long userId) {
        super("User with id: " + userId + " not founded");
    }
}
