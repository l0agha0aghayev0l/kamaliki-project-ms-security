package com.dea.ms_security.controller;

import org.springframework.web.bind.annotation.RestController;

import com.dea.ms_security.request.TokenRequest;
import com.dea.ms_security.response.TokenResponse;
import com.dea.ms_security.service.TokenService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/token")
@RequiredArgsConstructor
public class TokenController {


    private final TokenService tokenService;

    @PostMapping("/refresh")
    public ResponseEntity<TokenResponse> refreshAccessToken(@RequestBody TokenRequest token) {
    
        return ResponseEntity.ok().body(tokenService.refreshAccessToken(token));
    }
    
    
}
