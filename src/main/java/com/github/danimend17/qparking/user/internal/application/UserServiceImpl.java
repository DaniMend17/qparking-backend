package com.github.danimend17.qparking.user.internal.application;

import com.github.danimend17.qparking.user.*;
import com.github.danimend17.qparking.user.internal.web.UserMapper;
import com.github.danimend17.qparking.user.internal.infrastructure.persistence.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper mapper;

    @Override
    public UserDto getUserById(Long userId) {
        return userRepository.findById(userId)
                .map(mapper::toDto)
                .orElseThrow(() -> new UserNotFoundException(userId));
    }

    @Override
    public List<UserDto> getUsers() {
        return userRepository.findAll().stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public boolean userExistsById(Long userId) {
        return userRepository.existsById(userId);
    }

    @Override
    public UserDto getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(mapper::toDto)
                .orElseThrow(() -> new UserNotFoundException(email));
    }

    @Override
    public UserDto getUserByPhoneNumber(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber)
                .map(mapper::toDto)
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public List<UserSummaryDto> getAllSummariesWithIds(Collection<Long> userIds) {
        return userRepository.findSummariesByUserIds(userIds);
    }

}
