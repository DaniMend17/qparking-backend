package com.github.danimend17.qparking.user.internal.web;

import com.github.danimend17.qparking.user.CreateUserCommand;
import com.github.danimend17.qparking.user.UserService;
import com.github.danimend17.qparking.user.internal.UserMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper mapper;

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody CreateUserRequest request) {
        final CreateUserCommand createUserCommand = mapper.requestToCommand(request);
        final UserResponse userResponse = mapper.dtoToResponse(userService.createUser(createUserCommand));
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        final UserResponse userResponse = mapper.dtoToResponse(userService.getUserById(id));
        return ResponseEntity.ok(userResponse);
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<UserResponse> users = userService.getUsers().stream()
                .map(mapper::dtoToResponse)
                .toList();
        return ResponseEntity.ok(users);
    }

    @GetMapping(path = "phone-number-availability")
    public ResponseEntity<Map<String, Boolean>> checkPhoneNumberAvailability(@RequestParam String userPhoneNumber) {
        boolean available = !userService.isPhoneNumberAlreadyRegister(userPhoneNumber);
        return ResponseEntity.ok(Collections.singletonMap("available", available));
    }

}
