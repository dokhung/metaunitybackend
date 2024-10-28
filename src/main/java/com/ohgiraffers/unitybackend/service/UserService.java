package com.ohgiraffers.unitybackend.service;


import com.ohgiraffers.unitybackend.entity.UserEntity;
import com.ohgiraffers.unitybackend.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void saveUser(UserEntity user) {
        userRepository.save(user);
        System.out.println("성공");
    }

}
