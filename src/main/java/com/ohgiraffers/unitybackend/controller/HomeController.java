package com.ohgiraffers.unitybackend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/testServer")
@RequiredArgsConstructor
public class HomeController {

    // 테스트 매핑
    @GetMapping("/test")
    public String testMapping() {
        System.out.println("testMapping");
        return "test";
    }

}
