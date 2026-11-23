package com.github.danimend17.qparking.role;

public class RoleNameNotFoundException extends RuntimeException {

    public RoleNameNotFoundException(String roleName) {
        super("Role name: " + roleName + " does not exist");
    }
}
