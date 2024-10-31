package com.ohgiraffers.unitybackend.config;

import com.ohgiraffers.unitybackend.all.CustomOAuth2UserService;
import com.ohgiraffers.unitybackend.all.OAuth2SuccessHandler;
import com.ohgiraffers.unitybackend.all.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {
    private final CustomOAuth2UserService customOAuth2UserService;
    private final OAuth2SuccessHandler oAuth2SuccessHandler;

    private static final String[] WHITE_LIST = {
            "/api/auth/**",
            "/v3/api-docs/**",       // Swagger 문서
            "/swagger-ui/**",        // Swagger UI
            "/swagger-ui.html",     // Swagger UI HTML
            "/**",
            "/api/auth/google/**"
    };

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(csrfConfig -> csrfConfig.disable())
//                .headers(headerConfig -> headerConfig.frameOptions(frameOptionsConfig -> frameOptionsConfig.disable()))
//                .authorizeHttpRequests(authorizeRequest -> authorizeRequest
//                        .requestMatchers(WHITE_LIST).permitAll()
//                        .anyRequest().authenticated()
//                )
//                .logout(logoutConfig -> logoutConfig.logoutSuccessUrl("/"))
//                .oauth2Login(oauth -> oauth
//
//                        .userInfoEndpoint(userInfo -> userInfo.userService(customOAuth2UserService))
//                        .successHandler((request, response, authentication) -> {
//                            // DefaultOAuth2User로부터 사용자 정보 가져오기
//                            DefaultOAuth2User oAuth2User = (DefaultOAuth2User) authentication.getPrincipal();
//
//                            // 필요한 사용자 속성 추출
//                            String name = (String) oAuth2User.getAttribute("name");
//                            String email = (String) oAuth2User.getAttribute("email");
//
//                            // 기본 사용자 역할 설정 (예: Role.USER)
//                            Role defaultRole = Role.USER;
//
//                            // User 객체 생성 시 기본 Role 값을 함께 전달
//                            SessionUser user = new SessionUser(new User(name, email, defaultRole));
//
//
//                            // 세션에 SessionUser 객체 저장
//                            request.getSession().setAttribute("user", user);
//
//                            response.sendRedirect("/api/auth/google/callback");
//                        })
//                );
//
//
//        return http.build();
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrfConfig -> csrfConfig.disable())
                .headers(headerConfig -> headerConfig.frameOptions(frameOptionsConfig -> frameOptionsConfig.disable()))
                .authorizeHttpRequests(authorizeRequest -> authorizeRequest
                        .requestMatchers(WHITE_LIST).permitAll()
                        .anyRequest().authenticated()
                )
                .logout(logoutConfig -> logoutConfig.logoutSuccessUrl("/"))
                .oauth2Login(oauth -> oauth
                        .userInfoEndpoint(userInfo -> userInfo.userService(customOAuth2UserService))
                        .successHandler(oAuth2SuccessHandler) // Use the custom success handler
                );

        return http.build();
    }


}
