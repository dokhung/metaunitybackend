package com.ohgiraffers.unitybackend.all;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {

    private final JwtTokenProvider jwtTokenProvider;


    public OAuth2SuccessHandler(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        System.out.println("onAuthenticationSuccess() 메서드가 호출되었습니다.");

        // JWT 토큰 생성 및 출력
        try {
            String token = jwtTokenProvider.generateToken(authentication.getName());
            System.out.println("Generated JWT Token: " + token);
        } catch (Exception e) {
            System.out.println("Error generating JWT Token: " + e.getMessage());
        }

        // 리다이렉트
        response.sendRedirect("https://localhost:4040/api/auth/google/callback");
    }

}
