package com.ohgiraffers.unitybackend.config;

import com.ohgiraffers.unitybackend.all.CustomOAuth2UserService;
import com.ohgiraffers.unitybackend.all.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {
    private final CustomOAuth2UserService customOAuth2UserService;

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
//                .csrf(
//                        (csrfConfig) -> csrfConfig.disable()
//                )
//                .headers(
//                        (headerConfig) -> headerConfig.frameOptions(
//                                frameOptionsConfig -> frameOptionsConfig.disable()
//                        )
//                )
//                .authorizeHttpRequests((authorizeRequest) -> authorizeRequest
//                        .requestMatchers("/templates/posts/new", "/comments/save").hasRole(Role.USER.name())
//                        .requestMatchers(WHITE_LIST).permitAll()
//                        .anyRequest().authenticated()
//                )
//                .logout( // 로그아웃 성공 시 / 주소로 이동
//                        (logoutConfig) -> logoutConfig.logoutSuccessUrl("/")
//
//                )
//                // OAuth2 로그인 기능에 대한 여러 설정
//                .oauth2Login(Customizer.withDefaults()); // 아래 코드와 동일한 결과
//        /*
//                .oauth2Login(
//                        (oauth) ->
//                            oauth.userInfoEndpoint(
//                                    (endpoint) -> endpoint.userService(customOAuth2UserService)
//                            )
//                );
//        */
//
//        return http.build();
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrfConfig -> csrfConfig.disable())
                .headers(headerConfig -> headerConfig.frameOptions(frameOptionsConfig -> frameOptionsConfig.disable()))
                .authorizeHttpRequests(authorizeRequest -> authorizeRequest
                        .requestMatchers("/templates/posts/new", "/comments/save").hasRole("USER")
                        .requestMatchers(WHITE_LIST).permitAll()
                        .anyRequest().authenticated()
                )
                .logout(logoutConfig -> logoutConfig.logoutSuccessUrl("/"))
                .oauth2Login(oauth -> oauth
                        .userInfoEndpoint(userInfo -> userInfo.userService(customOAuth2UserService))
                        .successHandler((request, response, authentication) -> {
                            SessionUser user = (SessionUser) authentication.getPrincipal();
                            request.getSession().setAttribute("user", user);
                            response.sendRedirect("/api/auth/google/callback");
                        })
                );

        return http.build();
    }


}
