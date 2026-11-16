package com.github.danimend17.qparking.user.internal;

import com.github.danimend17.qparking.user.CreateUserCommand;
import com.github.danimend17.qparking.user.UserDTO;
import com.github.danimend17.qparking.user.internal.web.CreateUserRequest;
import com.github.danimend17.qparking.user.internal.web.UserResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO entityToDTO(User user);

    CreateUserCommand requestToCommand(CreateUserRequest createUserRequest);

    User commandToEntity(CreateUserCommand createUserCommand);

    UserResponse dtoToResponse(UserDTO userDTO);


}
