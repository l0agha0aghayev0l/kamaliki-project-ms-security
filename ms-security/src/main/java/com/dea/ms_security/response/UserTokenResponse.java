package com.dea.ms_security.response;

import lombok.Data;

import java.util.List;

@Data
public class UserTokenResponse {

    private String uuid;
    private String username;
    private List<String> roles;

}
