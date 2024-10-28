package com.ohgiraffers.unitybackend.repository;

import com.ohgiraffers.unitybackend.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, String> {
//    UserEntity findByGoogleId(String googleId);
}
