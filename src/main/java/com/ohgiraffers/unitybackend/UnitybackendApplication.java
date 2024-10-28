package com.ohgiraffers.unitybackend;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;


@SpringBootApplication
public class UnitybackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(UnitybackendApplication.class, args);
        System.out.println("Spring Boot Application 시작됨");
    }

}
