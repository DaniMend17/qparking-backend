package com.github.danimend17.qparking.security.internal.currentuser;

import com.github.danimend17.qparking.security.internal.userdetails.CustomUserDetails;
import com.github.danimend17.qparking.shared.identity.CurrentUserProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecurityCurrentUserProvider implements CurrentUserProvider {

    @Override
    public long getUserId() {
        final Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() ||
                !(authentication.getPrincipal() instanceof CustomUserDetails userDetails)) {
            throw new IllegalStateException("No authenticated user found");
        }
        return userDetails.userId();
    }
}
