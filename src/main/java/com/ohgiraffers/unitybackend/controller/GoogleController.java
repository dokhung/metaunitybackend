package com.ohgiraffers.unitybackend.controller;

import com.ohgiraffers.unitybackend.all.JwtTokenProvider;
import com.ohgiraffers.unitybackend.all.SessionUser;
import com.ohgiraffers.unitybackend.all.UserRepository;
import com.ohgiraffers.unitybackend.config.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class GoogleController {
    private final HttpSession httpSession;
    private final UserRepository userRepository; // UserRepository 주입

    public GoogleController(HttpSession httpSession, UserRepository userRepository) {
        this.httpSession = httpSession;
        this.userRepository = userRepository; // 주입된 UserRepository 사용
    }

    @GetMapping("/api/auth/google/callback")
    public ResponseEntity<String> getGoogleUser() {
        SessionUser user = (SessionUser) httpSession.getAttribute("user");

        if (user != null) {
            // 모든 사용자 정보를 콘솔에 출력
            System.out.println("User Details:");
            System.out.println("Name: " + user.getName());
            System.out.println("Email: " + user.getEmail());

            return ResponseEntity.ok("User: " + user.getName() + ", Email: " + user.getEmail());
        } else {
            System.out.println("User is null");
            return ResponseEntity.status(401).body("No user information found in session.");
        }
    }

    @GetMapping("/api/auth/user-info")
    public ResponseEntity<?> getUserInfo() {
        SessionUser sessionUser = (SessionUser) httpSession.getAttribute("user");

        if (sessionUser == null) {
            return ResponseEntity.status(401).body("No user is logged in.");
        }

        // UserRepository 인스턴스를 사용해 사용자 정보를 조회
        Optional<User> userOptional = userRepository.findByUserEmail(sessionUser.getEmail());

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return ResponseEntity.ok(user); // 사용자 정보 반환
        } else {
            return ResponseEntity.status(404).body("User not found in database.");
        }
    }
}
