package com.dea.ms_security.controller;

import com.dea.ms_security.response.UserDataResponse;
import com.dea.ms_security.response.UserTokenResponse;
import com.dea.ms_security.response.UsernameResponse;
import com.dea.ms_security.service.UserService;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dea.ms_security.service.TokenService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import static com.dea.ms_security.util.BaseHeaders.X_USER_ID;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;


@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final TokenService tokenService;
    private final UserService userService;

    @GetMapping("/username")
    public ResponseEntity<UsernameResponse> getActiveUser() {
        return ResponseEntity.ok().body(tokenService.getUsernameFromToken());
    }

    @GetMapping("/data")
    public ResponseEntity<UserDataResponse> getUserData(@RequestHeader(X_USER_ID) String userId) {
        var user = userService.getByUuid(userId);
        return ResponseEntity.ok().body(user);
    }
    
}
