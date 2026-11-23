package com.github.danimend17.qparking.user;

public interface UserAuthenticationService {

    UserAuthenticationDetails getUserByEmail(String email);

    UserAuthenticationDetails getUserById(Long userId);

}
