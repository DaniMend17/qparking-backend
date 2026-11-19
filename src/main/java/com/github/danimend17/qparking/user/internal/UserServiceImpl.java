package com.github.danimend17.qparking.user.internal;

import com.github.danimend17.qparking.user.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
class UserServiceImpl implements UserService {

    private final ApplicationEventPublisher eventPublisher;
    private final UserRepository userRepository;
    private final UserMapper mapper;

    @Override
    @Transactional
    public UserDto createUser(CreateUserCommand createUserCommand) {
        if (isPhoneNumberAlreadyRegister(createUserCommand.phoneNumber())) {
            throw new DuplicatedPhoneNumberException(createUserCommand.phoneNumber());
        }
        final User user = mapper.commandToEntity(createUserCommand);
        final User savedUser = userRepository.save(user);

        eventPublisher.publishEvent(new CreateUserEvent(savedUser.getUserId(),
                savedUser.getPhoneNumber()));

        return mapper.entityToDTO(savedUser);
    }

    @Override
    public UserDto getUserById(Long userId) {
        return userRepository.findById(userId)
                .map(mapper::entityToDTO)
                .orElseThrow(() -> new UserNotFoundException(userId));
    }

    @Override
    public List<UserDto> getUsers() {
        return userRepository.findAll().stream()
                .map(mapper::entityToDTO)
                .toList();
    }

    @Override
    public boolean isPhoneNumberAlreadyRegister(String phoneNumber) {
        return userRepository.existsByPhoneNumber(phoneNumber);
    }

    @Override
    public boolean userExistsById(Long userId) {
        return userRepository.existsById(userId);
    }

    @Override
    public UserDto getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(mapper::entityToDTO)
                .orElseThrow(() -> new UserNotFoundException(email));
    }


}
