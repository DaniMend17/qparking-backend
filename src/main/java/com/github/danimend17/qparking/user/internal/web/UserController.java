package com.github.danimend17.qparking.user.internal.web;

import com.github.danimend17.qparking.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper mapper;

    @GetMapping(path = "{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        final UserResponse userResponse = mapper.toResponse(userService.getUserById(id));
        return ResponseEntity.ok(userResponse);
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<UserResponse> users = userService.getUsers().stream()
                .map(mapper::toResponse)
                .toList();
        return ResponseEntity.ok(users);
    }

}
