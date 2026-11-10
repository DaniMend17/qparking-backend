package com.github.danimend17.qparking.user;


import com.github.danimend17.qparking.user.internal.UserEntity;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    UserEntity createUser(UserEntity user);

    Optional<UserEntity> getUserById(Long userId);

    List<UserEntity> getUsers();

}
