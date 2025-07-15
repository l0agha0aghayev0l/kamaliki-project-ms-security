package com.dea.ms_security.response;

import com.dea.ms_security.enumeration.TokenType;

public record TokenResponse(TokenType type, String token) {}
