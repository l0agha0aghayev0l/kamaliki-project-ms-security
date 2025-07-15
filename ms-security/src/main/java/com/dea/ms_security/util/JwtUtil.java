package com.dea.ms_security.util;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.dea.ms_security.dto.RoleDto;
import com.dea.ms_security.dto.UserDto;
import com.dea.ms_security.enumeration.AccessTokenClaimsEnum;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtUtil {

    private final String SECRET_KEY;
    private final long ACCESS_TOKEN_EXPIRATION_TIME;
    private final long REFRESH_TOKEN_EXPIRATION_TIME;
    private SecretKey secretKey;

    public JwtUtil(@Value("${jwt.secret}") String SECRET_KEY,
            @Value("${jwt.accessExpirationMs}") long ACCESS_TOKEN_EXPIRATION_TIME,
            @Value("${jwt.refreshExpirationMs}") long REFRESH_TOKEN_EXPIRATION_TIME) {
        this.SECRET_KEY = SECRET_KEY;
        System.out.println("Access token expiration time: " + ACCESS_TOKEN_EXPIRATION_TIME);
        System.out.println("Refresh token expiration time: " + REFRESH_TOKEN_EXPIRATION_TIME);

        this.ACCESS_TOKEN_EXPIRATION_TIME = (ACCESS_TOKEN_EXPIRATION_TIME);
        this.REFRESH_TOKEN_EXPIRATION_TIME = (REFRESH_TOKEN_EXPIRATION_TIME);
    }

    @PostConstruct
    private void init() {
        this.secretKey = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }

    public String generateRefreshToken(String userUuid) {
        return Jwts.builder()
                .subject(userUuid)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRATION_TIME))
                .signWith(secretKey)
                .compact();
    }

    public String generateAccessToken(UserDto userDto) {
        List<String> roleNames = userDto.getRoles().stream().map(RoleDto::getName).toList();
        return Jwts.builder()
                .subject(userDto.getUuid())
                .claim(AccessTokenClaimsEnum.ROLES.value, roleNames)
                .claim(AccessTokenClaimsEnum.USERNAME.value, userDto.getUsername())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRATION_TIME))
                .signWith(secretKey)
                .compact();
    }

    public Claims getAllClaims(String token) {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().verifyWith(secretKey).build().parse(token);
            return true;
        } catch (SecurityException e) {
            log.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }

}
