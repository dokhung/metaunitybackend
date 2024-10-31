package com.ohgiraffers.unitybackend.controller;

import com.ohgiraffers.unitybackend.all.JwtTokenProvider;
import com.ohgiraffers.unitybackend.all.SessionUser;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    private final JwtTokenProvider jwtTokenProvider;
    private final HttpSession httpSession;

    public AuthController(JwtTokenProvider jwtTokenProvider, HttpSession httpSession) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.httpSession = httpSession;
    }

    @GetMapping("/api/auth/refresh")
    public ResponseEntity<String> refreshToken() {
        SessionUser user = (SessionUser) httpSession.getAttribute("user");

        if (user != null) {
            // Validate the existing refresh token (implement validation logic)
            // Generate a new JWT token
            String newToken = jwtTokenProvider.generateToken(user.getEmail());
            return ResponseEntity.ok(newToken);
        } else {
            return ResponseEntity.status(401).body("No user information found in session.");
        }
    }
}

