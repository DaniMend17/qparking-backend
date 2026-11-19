package com.github.danimend17.qparking.security.internal;

import com.github.danimend17.qparking.role.UserRoleDto;
import com.github.danimend17.qparking.role.UserRoleQueryService;
import com.github.danimend17.qparking.user.UserAuthenticationDetails;
import com.github.danimend17.qparking.user.UserAuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserAuthenticationService userAuthenticationService;
    private final UserRoleQueryService userRoleQueryService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final UserAuthenticationDetails user = userAuthenticationService.getUserByEmail(username);

        final List<SimpleGrantedAuthority> userRoles = userRoleQueryService.getUserRoles(user.userId())
                .stream()
                .map(UserRoleDto::name)
                .map(SimpleGrantedAuthority::new)
                .toList();

        return new CustomUserDetails(
                user.userId(),
                user.email(),
                user.password(),
                userRoles
        );
    }
}
