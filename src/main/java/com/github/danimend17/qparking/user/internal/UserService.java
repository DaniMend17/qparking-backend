package com.github.danimend17.qparking.user.internal;

import com.github.danimend17.qparking.user.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;

    @Override
    public UserEntity createUser(UserEntity user) {
        return null;
    }

    @Override
    public Optional<UserEntity> getUserById(Long userId) {
        return Optional.empty();
    }

    @Override
    public List<UserEntity> getUsers() {
        return List.of();
    }
}
