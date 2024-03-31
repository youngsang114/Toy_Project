package com.shop.config;

import com.shop.config.jwt.*;
import com.shop.refressh.repository.RefreshRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

    private final JwtUtil jwtUtil;
    private final AuthenticationConfiguration authenticationConfiguration;
    private final RefreshRepository refreshRepository;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        return http
                .csrf((auth)->auth.disable())
                .formLogin((auth)->auth.disable())
                .httpBasic((auth)->auth.disable())
                // 경로별 인가 작업
                .authorizeHttpRequests((auth)->auth
                        .requestMatchers("/login","/","/members/new").permitAll()
                        .requestMatchers("/admin").hasRole("ADMIN")
                        .requestMatchers("/reissue").permitAll()
                        .anyRequest().authenticated())
                .addFilterBefore(new JwtFilter(jwtUtil), LoginFilter.class)
                .addFilterAt(new LoginFilter(authenticationManager(authenticationConfiguration),jwtUtil,refreshRepository), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new JwtExceptionHandler(), JwtFilter.class)
                .addFilterBefore(new CustomLogoutFilter(jwtUtil, refreshRepository), LogoutFilter.class)
                // jwt는 session을 stateless하게 관리
                .sessionManagement((session)-> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }
    // authenticationManager을 Bean으로 등록!
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception{
        return configuration.getAuthenticationManager();
    }
}
