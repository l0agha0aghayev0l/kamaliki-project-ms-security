package com.dea.ms_security.request;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegistrationRequest(
    @NotBlank @Length(min = 6, max = 16) String username,
    @NotBlank String name,
    @NotBlank String surname,
    @NotBlank @Email String email,
    @NotBlank @Length(min = 8, max = 16) String password,
    @NotBlank String confirmPassword
) {
    
}
