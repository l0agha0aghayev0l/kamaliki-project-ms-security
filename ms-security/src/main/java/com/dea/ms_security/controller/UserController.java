package com.dea.ms_security.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dea.ms_security.service.TokenService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final TokenService tokenService;

    @GetMapping("/username")
    public ResponseEntity<String> getActiveUser() {
        return ResponseEntity.ok().body(tokenService.getUsernameFromToken());
    }
    
}
