package com.ohgiraffers.unitybackend.all;

import com.ohgiraffers.unitybackend.config.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    // 인증된 사용자 정보만 필요 => name, email, picture 필드만 선언
    private String name;
    private String email;

    public SessionUser(User user) {
        this.name = user.getUserName();
        this.email = user.getUserEmail();

    }
}
