package com.dea.ms_security.implementations;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.dea.ms_security.entity.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserDetailsImplementation implements UserDetails {

    private final User USER;

    public User getUSER() {
        return USER;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return USER.getRoles()
                .stream()
                .map(role -> (GrantedAuthority) role::getName)
                .toList();
    }

    @Override
    public String getPassword() {
        return USER.getPassword();
    }

    @Override
    public String getUsername() {
        return USER.getUsername();
    }

}
