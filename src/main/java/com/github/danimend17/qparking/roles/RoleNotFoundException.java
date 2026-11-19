package com.github.danimend17.qparking.roles;

public class RoleNotFoundException extends RuntimeException {
    public RoleNotFoundException(Long roleId) {
        super("Role with id: " + roleId + " does not exist");
    }
}
