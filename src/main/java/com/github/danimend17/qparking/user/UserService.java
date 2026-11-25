package com.github.danimend17.qparking.user;


import java.util.List;

public interface UserService {

    UserDto getUserById(Long userId);

    List<UserDto> getUsers();

    boolean userExistsById(Long userId);

    UserDto getUserByEmail(String email);

    UserDto getUserByPhoneNumber(String phoneNumber);
}
