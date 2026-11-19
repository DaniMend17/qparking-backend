package com.github.danimend17.qparking.role.internal.application.assignment;

import com.github.danimend17.qparking.user.CreateUserEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AssignUserRoleEventListener {

    private final UserRoleAssignmentService userRoleAssignmentService;

    @EventListener
    public void onUserCreated(CreateUserEvent event) {
        userRoleAssignmentService.assignDefaultRole(event.userId());
    }

}
