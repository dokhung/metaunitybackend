package com.ohgiraffers.unitybackend.dto;

import java.time.Instant;

public class UserDTO {
    private String googleId;
    private String email;
    private String name;

    // JWT 관련 필드
    private String jwtToken;         // JWT 토큰
    private Instant tokenIssuedAt;   // 토큰 발급 시간
    private Instant tokenExpiresAt;  // 토큰 만료 시간

    // Getters and Setters
    public String getGoogleId() {
        return googleId;
    }

    public void setGoogleId(String googleId) {
        this.googleId = googleId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public Instant getTokenIssuedAt() {
        return tokenIssuedAt;
    }

    public void setTokenIssuedAt(Instant tokenIssuedAt) {
        this.tokenIssuedAt = tokenIssuedAt;
    }

    public Instant getTokenExpiresAt() {
        return tokenExpiresAt;
    }

    public void setTokenExpiresAt(Instant tokenExpiresAt) {
        this.tokenExpiresAt = tokenExpiresAt;
    }
}
