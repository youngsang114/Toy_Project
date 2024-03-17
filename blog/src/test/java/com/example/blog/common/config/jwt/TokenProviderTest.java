package com.example.blog.common.config.jwt;

import com.example.blog.domain.User;
import com.example.blog.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.charset.StandardCharsets;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class TokenProviderTest {
    @Autowired private TokenProvider tokenProvider;
    @Autowired private UserRepository userRepository;
    @Autowired private JwtProperties jwtProperties;

    @DisplayName("generateToken():유저 정보와 만료 기간을 가지고 토큰을 만들기")
    @Test
    void generateToken(){
        //given
        User testUser = userRepository.save(
                User.builder()
                        .email("user@gmail.com")
                        .password("test")
                        .build());

        // when
        String token = tokenProvider.generateToken(testUser, Duration.ofDays(14));

        // then
        Long userId = Jwts.parserBuilder()
                .setSigningKey(jwtProperties.getSecretKey().getBytes(StandardCharsets.UTF_8))
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("id", Long.class);

        Assertions.assertThat(userId).isEqualTo(testUser.getId());
    }
}