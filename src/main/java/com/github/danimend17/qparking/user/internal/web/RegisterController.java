package com.github.danimend17.qparking.user.internal.web;

import com.github.danimend17.qparking.user.internal.application.register.RegisterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping(path = "register")
@RequiredArgsConstructor
public class RegisterController {

    private final RegisterService registerService;
    private final UserMapper mapper;

    @PostMapping
    public ResponseEntity<UserResponse> registerUser(@Valid @RequestBody CreateUserRequest request) {
        final UserResponse userResponse = mapper.toResponse(registerService.registerUser(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }

    @GetMapping(path = "phone-number-availability")
    public ResponseEntity<Map<String, Boolean>> checkPhoneNumberAvailability(@RequestParam String phoneNumber) {
        boolean available = !registerService.isPhoneNumberAlreadyRegister(phoneNumber);
        return ResponseEntity.ok(Collections.singletonMap("available", available));
    }

}
