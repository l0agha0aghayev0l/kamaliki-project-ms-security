package com.dea.ms_security.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Integer id;
    private String uuid;
    private String username;
    private String name;
    private String surname;
    private String email;
    private String password;
    private List<RoleDto> roles;
}
