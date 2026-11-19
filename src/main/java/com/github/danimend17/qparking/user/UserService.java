package com.github.danimend17.qparking.user;


import java.util.List;

public interface UserService {

    UserDto createUser(CreateUserCommand createUserCommand);

    UserDto getUserById(Long userId);

    List<UserDto> getUsers();

    boolean isPhoneNumberAlreadyRegister(String phoneNumber);

    boolean userExistsById(Long userId);

    UserDto getUserByEmail(String email);

}
