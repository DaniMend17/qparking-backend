package com.github.danimend17.qparking.roles.internal.domain.assignment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AssignUserRoleEventListener {

    private final UserRoleRepository userRoleRepository;


}
