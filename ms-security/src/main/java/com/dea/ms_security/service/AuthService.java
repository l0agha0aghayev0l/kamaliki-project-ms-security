package com.dea.ms_security.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.dea.ms_security.enumeration.TokenType;
import com.dea.ms_security.implementations.UserDetailsImplementation;
import com.dea.ms_security.mapper.UserMapper;
import com.dea.ms_security.request.LoginRequest;
import com.dea.ms_security.response.TokenResponse;
import com.dea.ms_security.util.JwtUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public Set<TokenResponse> login(LoginRequest loginRequest) {
        Set<TokenResponse> tokens = new HashSet<>();

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password()));

        UserDetailsImplementation userDetails = (UserDetailsImplementation) authentication.getPrincipal();

        tokens.add(new TokenResponse(TokenType.ACCESS_TOKEN, jwtUtil.generateAccessToken(UserMapper.INSTANCE.toUserDto(userDetails.getUSER()))));
        tokens.add(new TokenResponse(TokenType.REFRESH_TOKEN, jwtUtil.generateRefreshToken(userDetails.getUSER().getUuid())));
        return tokens;
    }

}
