package com.dea.ms_security.request;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest (
    @NotBlank String username,
    @NotBlank String password
) {
    
}
