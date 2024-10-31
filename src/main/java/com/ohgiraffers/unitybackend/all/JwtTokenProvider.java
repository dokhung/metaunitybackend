package com.ohgiraffers.unitybackend.all;

import com.ohgiraffers.unitybackend.config.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;

import java.security.Key;
import java.util.Date;
import java.util.List;

import io.jsonwebtoken.Jwts;

public class JwtTokenProvider {
    private final Key key = Keys.hmacShaKeyFor("1476986843aa1e80c8374122a839da127ce0abcb76a60986ad7eb887a2c32fe93262cd7b8687a43eab82715e042a2646e0b73eaf2b94c80888a8408298187b67".getBytes());
    private final long EXPIRATION_TIME = 86400000L; // 1 day in milliseconds

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
}
