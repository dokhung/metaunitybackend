package com.ohgiraffers.unitybackend.controller;

import com.ohgiraffers.unitybackend.all.SessionUser;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
@Slf4j
@Tag(name = "구글 로그인 API", description = "구글 로그인 및 회원가입 기능을 제공하는 API")
public class GoogleController {

    @GetMapping("google/login")
    public String login() {
        System.out.println("login");
        return "login";
    }

    @GetMapping("/google/callback")
    public String callback(HttpSession session) {
        SessionUser user = (SessionUser) session.getAttribute("user");
        if (user != null) {
            log.info("Logged in user: {}", user.getEmail());
            return "Successfully logged in as " + user.getName();
        } else {
            log.warn("User information not found in session");
            return "User information not found";
        }
    }


}
