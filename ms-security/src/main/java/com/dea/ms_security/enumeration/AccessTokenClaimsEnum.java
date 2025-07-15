package com.dea.ms_security.enumeration;

public enum AccessTokenClaimsEnum {
    USERNAME("username"), 
    ROLES("roles");

    public final String value;

    private AccessTokenClaimsEnum(String value) {
        this.value = value;
    }
}
