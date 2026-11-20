package com.github.danimend17.qparking.user.internal.application;

import com.github.danimend17.qparking.user.CreateUserEvent;
import com.github.danimend17.qparking.user.UserDto;
import com.github.danimend17.qparking.user.internal.domain.DuplicatedEmailException;
import com.github.danimend17.qparking.user.internal.domain.DuplicatedPhoneNumberException;
import com.github.danimend17.qparking.user.internal.domain.User;
import com.github.danimend17.qparking.user.internal.infrastructure.persistence.UserRepository;
import com.github.danimend17.qparking.user.internal.web.CreateUserRequest;
import com.github.danimend17.qparking.user.internal.web.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class RegisterServiceImpl implements RegisterService {

    private final UserRepository userRepository;
    private final UserMapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public UserDto registerUser(CreateUserRequest createUserRequest) {
        if (userRepository.existsByEmail(createUserRequest.email())) {
            throw new DuplicatedEmailException(createUserRequest.email());
        }
        if (isPhoneNumberAlreadyRegister(createUserRequest.phoneNumber())) {
            throw new DuplicatedPhoneNumberException(createUserRequest.phoneNumber());
        }

        final User user = mapper.fromRequestModel(createUserRequest);
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        final User savedUser = userRepository.save(user);

        eventPublisher.publishEvent(new CreateUserEvent(savedUser.getUserId(),
                savedUser.getPhoneNumber()));

        return mapper.entityToDTO(savedUser);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isPhoneNumberAlreadyRegister(String phoneNumber) {
        return userRepository.existsByPhoneNumber(phoneNumber);
    }
}
