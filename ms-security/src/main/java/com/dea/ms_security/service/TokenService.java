package com.dea.ms_security.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.dea.ms_security.dto.UserDto;
import com.dea.ms_security.enumeration.TokenType;
import com.dea.ms_security.error.InvalidTokenException;
import com.dea.ms_security.mapper.UserMapper;
import com.dea.ms_security.repository.UserRepository;
import com.dea.ms_security.request.TokenRequest;
import com.dea.ms_security.response.TokenResponse;
import com.dea.ms_security.util.JwtUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    public TokenResponse refreshAccessToken(TokenRequest tokenRequest) {
        String refreshToken = tokenRequest.getToken();
        if (jwtUtil.validateToken(refreshToken)) {
            String uuid = jwtUtil.getAllClaims(refreshToken).getSubject();
            UserDto userDto = UserMapper.INSTANCE.toUserDto(userRepository.findByUuid(uuid));
            return new TokenResponse(TokenType.ACCESS_TOKEN, jwtUtil.generateAccessToken(userDto));
        }
        throw new InvalidTokenException("Invalid refresh token");
    }

    public String getUsernameFromToken() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();

        if (principal instanceof UserDetails userDetails) {
            return userDetails.getUsername();
        } else {
            return principal.toString();
        }
    }

}
