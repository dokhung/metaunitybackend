package com.ohgiraffers.unitybackend.config;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "tbl_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long id;

    @NotNull
    @Column(name = "USER_NAME")
    private String userName;

    @NotNull
    @Column(name = "USER_EMAIL", unique = true) // 이메일을 고유하게 설정하여 중복 방지
    private String userEmail;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "USER_ROLE")
    private Role role;

    @Builder
    public User(Long id, String userName, String userEmail, Role role) {
        this.id = id;
        this.userName = userName;
        this.userEmail = userEmail;
        this.role = role;
    }

    // 편의 생성자
    public User(String userName, String userEmail, Role role) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.role = role;
    }

    // 이름 업데이트 메서드
    public User update(String userName) {
        this.userName = userName;
        return this;
    }

    // 역할 키 반환 메서드
    public String getRoleKey() {
        return this.role.getKey();
    }
}
