package com.github.danimend17.qparking.user.internal;

import com.github.danimend17.qparking.user.CreateUserCommand;
import com.github.danimend17.qparking.user.UserDto;
import com.github.danimend17.qparking.user.internal.web.CreateUserRequest;
import com.github.danimend17.qparking.user.internal.web.UserResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto entityToDTO(User user);

    CreateUserCommand requestToCommand(CreateUserRequest createUserRequest);

    User commandToEntity(CreateUserCommand createUserCommand);

    UserResponse dtoToResponse(UserDto userDTO);


}
