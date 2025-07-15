package com.dea.ms_security.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.dea.ms_security.dto.UserDto;
import com.dea.ms_security.enumeration.TokenType;
import com.dea.ms_security.error.InvalidTokenException;
import com.dea.ms_security.mapper.UserMapper;
import com.dea.ms_security.repository.UserRepository;
import com.dea.ms_security.response.TokenResponse;
import com.dea.ms_security.util.JwtUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    
    public List<TokenResponse> refreshAccessToken(String refreshToken) {
        if (jwtUtil.validateToken(refreshToken)) {
            List<TokenResponse> responses = new ArrayList<>();
            String uuid = jwtUtil.getAllClaims(refreshToken).getSubject();
            UserDto userDto = UserMapper.INSTANCE.toUserDto(userRepository.findByUuid(uuid));
            responses.add(new TokenResponse(TokenType.REFRESH_TOKEN, refreshToken));
            responses.add(new TokenResponse(TokenType.ACCESS_TOKEN, jwtUtil.generateAccessToken(userDto)));
            return responses;
        }
        throw new InvalidTokenException("Invalid refresh token");
    }
}
