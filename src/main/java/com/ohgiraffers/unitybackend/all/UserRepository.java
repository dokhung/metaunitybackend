package com.ohgiraffers.unitybackend.all;


import com.ohgiraffers.unitybackend.config.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserEmail(String userEmail); // 중복 가입 확인
}
