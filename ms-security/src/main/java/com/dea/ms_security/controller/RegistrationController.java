package com.dea.ms_security.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dea.ms_security.enumeration.UserRoleEnum;
import com.dea.ms_security.request.RegistrationRequest;
import com.dea.ms_security.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/registration")
public class RegistrationController {

    private final UserService userService;

    // Registration for CUSTOMER
    @PostMapping("/customer")
    public ResponseEntity<Map<String, String>> registerCustomer(@RequestBody @Valid RegistrationRequest registrationRequest) {
        userService.registration(registrationRequest, UserRoleEnum.ROLE_CUSTOMER);
        return ResponseEntity.ok(Map.of("message", "Registration successful"));
    }
    // Registration for ADMIN

    @PostMapping("/admin")
    public ResponseEntity<Map<String, String>> registerAdmin(@RequestBody @Valid RegistrationRequest registrationRequest) {
        userService.registration(registrationRequest, UserRoleEnum.ROLE_ADMIN);
        return ResponseEntity.ok(Map.of("message", "Registration successful"));
    }
}
