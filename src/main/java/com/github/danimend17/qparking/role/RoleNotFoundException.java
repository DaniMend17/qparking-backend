package com.github.danimend17.qparking.role;

public class RoleNotFoundException extends RuntimeException {
    public RoleNotFoundException(Long roleId) {
        super("Role with id: " + roleId + " does not exist");
    }
}
