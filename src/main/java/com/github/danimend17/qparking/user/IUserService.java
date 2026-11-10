package com.github.danimend17.qparking.user;


import java.util.List;

public interface IUserService {

    UserDTO createUser(CreateUserCommand createUserCommand);

    UserDTO getUserById(Long userId);

    List<UserDTO> getUsers();

    boolean isPhoneNumberAlreadyRegister(String phoneNumber);

}
