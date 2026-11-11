package com.github.danimend17.qparking.user.internal;

import com.github.danimend17.qparking.user.CreateUserCommand;
import com.github.danimend17.qparking.user.IUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "user")
@RequiredArgsConstructor
class UserController {

    private final IUserService userService;
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

    @GetMapping(path = "phone-number/{userPhoneNumber}")
    public ResponseEntity<Map<String, Boolean>> checkPhoneNumberAvailability(@PathVariable String userPhoneNumber) {
        boolean phoneNumberAvailable = userService.isPhoneNumberAlreadyRegister(userPhoneNumber);
        return ResponseEntity.ok(Collections.singletonMap("alreadyRegister", phoneNumberAvailable));
    }

}
