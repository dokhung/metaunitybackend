package com.ohgiraffers.unitybackend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "TBL_USER")
public class UserEntity {

    @Id
    @Column(name = "USER_ID", nullable = false, unique = true)
    private String userId;

    @Column(name = "USER_NAME", nullable = false)
    private String useName;

    @Column(name = "USER_EMAIL", nullable = false, unique = true)
    private String userEmail;

    @Column(name = "USER_PASSWORD", nullable = false)
    private String userPassword;

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;


    public UserEntity(String userId, String useName, String userEmail, String userPassword, LocalDateTime createdAt) {
        this.userId = userId;
        this.useName = useName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "userId='" + userId + '\'' +
                ", useName='" + useName + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
