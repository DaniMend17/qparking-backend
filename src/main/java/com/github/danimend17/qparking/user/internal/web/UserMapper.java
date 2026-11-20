package com.github.danimend17.qparking.user.internal.web;

import com.github.danimend17.qparking.user.UserDto;
import com.github.danimend17.qparking.user.internal.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(User user);

    UserResponse toResponse(UserDto userDTO);

    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "credit", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    User toModel(CreateUserRequest request);

}
