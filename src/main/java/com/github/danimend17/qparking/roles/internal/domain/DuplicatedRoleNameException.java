package com.github.danimend17.qparking.roles.internal.domain;

public class DuplicatedRoleNameException extends RuntimeException {
    public DuplicatedRoleNameException(String roleName) {
        super("There is already a role with name: " + roleName + " registered");
    }
}
