package com.ohgiraffers.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.IdTokenClaimNames;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    @Value("${google.client.id}")
    private String clientId;

    @Value("${google.client.secret}")
    private String clientSecret;
//    private final OAuth2SuccessHandler oAuth2SuccessHandler;
    @Value("${google.redirect.uri}")
    private String redirectUri;
    @Value("${google.redirect.successfulUri}")
    private String successfulUri;
    private static final String[] WHITE_LIST = {
            "/api/auth/**",
            "/v3/api-docs/**",       // Swagger 문서
            "/swagger-ui/**",        // Swagger UI
            "/swagger-ui.html",     // Swagger UI HTML

    };

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .requiresChannel(channel -> channel.anyRequest().requiresSecure())
                .cors(cors -> cors.configurationSource(corsConfigurationSource())) // CORS 설정 적용
                .csrf(csrf -> csrf.disable())    // CSRF 보호 비활성화
                // 요청에 대한 권한 설정
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(WHITE_LIST).permitAll()
                        .anyRequest().authenticated()
                )
                // OAuth2 로그인 설정
                .oauth2Login(oauth2 -> oauth2

                        .authorizationEndpoint(authorization -> authorization
                                .baseUri("/oauth2/authorization"))
                        .redirectionEndpoint(redirection -> redirection
                                .baseUri("/login/oauth2/code/*"))
                        .userInfoEndpoint(userInfo -> userInfo
                                .userService(this.oauth2UserService()))
//                        .successHandler(oAuth2SuccessHandler)
                );

        return http.build();
    }
    private OAuth2UserService<OAuth2UserRequest, OAuth2User> oauth2UserService() {
//        final PrincipalOauthUserService delegate = new PrincipalOauthUserService(passwordEncoder(), userRegistService,userCommandRepository);
//        return (userRequest) -> {
//
//            // Delegate to the default implementation for loading a user
//            return (CustomUserDetail) delegate.loadUser(userRequest);
//        };

        return null;
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();

        config.setAllowCredentials(true);
        config.setAllowedOrigins(Arrays.asList(
                "http://localhost:4040/**"
                ,"http://localhost:4040/login/oauth2/code/google"
        ));
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(Arrays.asList("*"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
    private ClientRegistration googleClientRegistration() {
        return ClientRegistration.withRegistrationId("google")
                .clientId(clientId)
                .clientSecret(clientSecret)
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .redirectUri("http://localhost:4040/login/oauth2/code/google")
                .scope("profile", "email")
                .authorizationUri("https://accounts.google.com/o/oauth2/v2/auth")
                .tokenUri("https://www.googleapis.com/oauth2/v4/token")
                .userInfoUri("https://www.googleapis.com/oauth2/v3/userinfo")
                .userNameAttributeName(IdTokenClaimNames.SUB)
                .jwkSetUri("https://www.googleapis.com/oauth2/v3/certs")
                .clientName("Google")
                .build();
    }
}
