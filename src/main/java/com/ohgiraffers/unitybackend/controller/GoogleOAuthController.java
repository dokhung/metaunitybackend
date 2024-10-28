package com.ohgiraffers.unitybackend.controller;

import com.ohgiraffers.unitybackend.entity.UserEntity;
import com.ohgiraffers.unitybackend.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "구글 로그인 API", description = "구글 로그인 및 회원가입 기능을 제공하는 API")
public class GoogleOAuthController {

    // https://localhost:4040/
    @Value("${GOOGLE_CLIENT_ID}")
    private String googleClientId;

    @Value("${GOOGLE_CLIENT_SECRET}")
    private String clientSecret;

    @Value("https://accounts.google.com/o/oauth2/v2/auth")
    private String authorizationUri;

    @Value("https://localhost:4040/api/auth/google/login")
    private String googleRedirectUri;

    @Value("profile email")
    private String scope;

    @GetMapping("/google/redirect")
    public void redirectToGoogle(HttpServletResponse response) throws IOException {
        String redirectUri = authorizationUri + "?"
                + "client_id=" + googleClientId
                + "&redirect_uri=" + googleRedirectUri
                + "&response_type=code"
                + "&scope=" + scope;
        response.sendRedirect(redirectUri);
    }

    @GetMapping("/google/login")
    public void  googleLogin(HttpServletResponse response, HttpServletRequest request, @RequestParam(name = "code") String code) throws IOException {
        System.out.println("Google login started with code: " + code);
        System.out.println("Current Url : " + request.getRequestURL());
        System.out.println("response" + response);
    }

    @GetMapping("google/test")
    public ResponseEntity<String> testMapping() {
        return ResponseEntity.ok("서버가 정상적으로 동작하고 있습니다.");
    }
}
