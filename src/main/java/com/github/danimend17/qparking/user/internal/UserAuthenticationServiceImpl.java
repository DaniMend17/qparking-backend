package com.github.danimend17.qparking.user.internal;

import com.github.danimend17.qparking.user.UserAuthenticationDetails;
import com.github.danimend17.qparking.user.UserAuthenticationService;
import com.github.danimend17.qparking.user.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAuthenticationServiceImpl implements UserAuthenticationService {

    private final UserRepository userRepository;

    @Override
    public UserAuthenticationDetails getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(user -> new UserAuthenticationDetails(
                        user.getUserId(),
                        user.getEmail(),
                        user.getPassword()
                ))
                .orElseThrow(() -> new UserNotFoundException(email));
    }
}
