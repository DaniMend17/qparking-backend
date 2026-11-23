package com.github.danimend17.qparking.auth.internal;

public interface AuthService {

    AuthResponse login(String email, String password);

}
