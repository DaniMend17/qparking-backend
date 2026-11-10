package com.github.danimend17.qparking.user.internal;

import com.github.danimend17.qparking.user.CreateUserCommand;
import com.github.danimend17.qparking.user.UserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO entityToDTO(UserEntity userEntity);

    CreateUserCommand requestToCommand(CreateUserRequest createUserRequest);

    UserEntity commandToEntity(CreateUserCommand createUserCommand);

    UserResponse dtoToResponse(UserDTO userDTO);


}
