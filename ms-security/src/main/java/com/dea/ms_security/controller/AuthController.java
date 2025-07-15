package com.dea.ms_security.controller;

import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dea.ms_security.request.LoginRequest;
import com.dea.ms_security.response.TokenResponse;
import com.dea.ms_security.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {    
    //login

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<Set<TokenResponse>> login(@RequestBody LoginRequest loginRequest) {
        
        return ResponseEntity.ok(authService.login(loginRequest));
    }
}
