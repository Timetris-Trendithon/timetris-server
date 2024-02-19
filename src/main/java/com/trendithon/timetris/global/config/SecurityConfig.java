package com.trendithon.timetris.global.config;

import com.trendithon.timetris.global.auth.oauth.handler.OAuth2SuccessHandler;
import com.trendithon.timetris.global.auth.oauth.service.CustomOAuthService;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer.FrameOptionsConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final OAuth2SuccessHandler oAuth2SuccessHandler;
    private final CustomOAuthService customOAuthService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .logout(AbstractHttpConfigurer::disable)
                .headers(c -> c.frameOptions(
                        FrameOptionsConfig::disable).disable()); // X-Frame-Options 비활성화

        http.sessionManagement((session) -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.authorizeRequests()
                .requestMatchers("/","/css/**","/images/**","/js/**","/favicon.ico","/h2-console/**", "/index.html").permitAll()
                .requestMatchers("/login").permitAll() // 모든 유저 접근 가능 (인증 필요 X)
                .requestMatchers(
                        // Swagger 허용 URL
                        "/v2/api-docs", "/v3/api-docs", "/v3/api-docs/**", "/swagger-resources",
                        "/swagger-resources/**", "/configuration/ui", "/configuration/security", "/swagger-ui/**",
                        "/webjars/**", "/swagger-ui.html").permitAll()
                //.requestMatchers("/main").hasRole("USER") // "/main/authenticated"에 대한 접근은 "USER" 역할이 있어야 함
                .requestMatchers("/api/**").authenticated() // 인증 필요

                .anyRequest().permitAll();

        http    // oauth2 설정
                .oauth2Login(oauth -> // OAuth2 로그인 기능에 대한 여러 설정의 진입점
                        // OAuth2 로그인 성공 이후 사용자 정보를 가져올 때의 설정을 담당
                        oauth.userInfoEndpoint(c -> c.userService(customOAuthService))
                                // 로그인 성공 시 핸들러
                                .successHandler(oAuth2SuccessHandler)
                )
//                .logout(logout -> logout
//                        .logoutSuccessUrl("/login"));
                .logout(logout ->logout
                .logoutUrl("/logout") // Customize the logout URL if needed
                .logoutSuccessUrl("/login?logout") // Redirect to login page after logout
                .invalidateHttpSession(true) // Invalidate HTTP session
                .clearAuthentication(true) // Clear user authentication
                .deleteCookies("JSESSIONID") // Delete cookies if any
                .logoutSuccessHandler((request, response, authentication) -> {
                    // Perform additional logout actions if needed
                    response.setStatus(HttpStatus.OK.value());
                })
                .permitAll()); // Allow all users to logout


        return http.build();
    }
}