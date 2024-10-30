package com.ohgiraffers.unitybackend.config;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("USER_ID")
    @NotNull
    private Long id;

    @NotNull
    @JsonProperty("USER_NAME")
    @Column(name = "USER_NAME")
    private String userName;

    @NotNull
    @JsonProperty("USER_EMAIL")
    @Column(name = "USER_EMAIL")
    private String userEmail;

    @Enumerated(EnumType.STRING) // Enum 타입은 문자열 형태로 저장해야 함
    @NotNull
    @JsonProperty("USER_ROLE")
    @Column(name = "USER_ROLE")
    private com.ohgiraffers.unitybackend.config.Role role;

    @Builder
    public User(Long id, String userName, String userEmail, Role role) {
        this.id = id;
        this.userName = userName;
        this.userEmail = userEmail;
        this.role = role;
    }

    public User update(String UserName){
        this.userName = UserName;
        return this;
    }

    public String getRoleKey(){
        return this.role.getKey();
    }
}
