package com.github.danimend17.qparking.user.internal.application.register;

import com.github.danimend17.qparking.user.UserDto;
import com.github.danimend17.qparking.user.internal.web.CreateUserRequest;

public interface RegisterService {
    UserDto registerUser(CreateUserRequest request);

    boolean isPhoneNumberAlreadyRegister(String phoneNumber);
}
