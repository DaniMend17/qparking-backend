package com.github.danimend17.qparking.user.internal;

import com.github.danimend17.qparking.user.CreateUserCommand;
import com.github.danimend17.qparking.user.IUserService;
import com.github.danimend17.qparking.user.UserDTO;
import com.github.danimend17.qparking.user.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
class UserService implements IUserService {

    private final UserRepository userRepository;
    private final UserMapper mapper;

    @Override
    @Transactional
    public UserDTO createUser(CreateUserCommand createUserCommand) {
        final UserEntity userEntity = mapper.commandToEntity(createUserCommand);
        final UserEntity savedUser = userRepository.save(userEntity);
        return mapper.entityToDTO(savedUser);
    }

    @Override
    public UserDTO getUserById(Long userId) {
        return userRepository.findById(userId)
                .map(mapper::entityToDTO)
                .orElseThrow(() -> new UserNotFoundException(userId));
    }

    @Override
    public List<UserDTO> getUsers() {
        return userRepository.findAll().stream()
                .map(mapper::entityToDTO)
                .toList();
    }

    @Override
    public boolean isPhoneNumberAlreadyRegister(String phoneNumber) {
        return userRepository.existsByPhoneNumber(phoneNumber);
    }
}
