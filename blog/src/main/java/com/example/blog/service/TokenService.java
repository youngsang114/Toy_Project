package com.example.blog.service;

import com.example.blog.common.config.jwt.TokenProvider;
import com.example.blog.common.error.TokenError;
import com.example.blog.common.exception.TokenException;
import com.example.blog.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;

@RequiredArgsConstructor
@Service
public class TokenService {

    private final TokenProvider tokenProvider;
    private final RefreshTokenService refreshTokenService;
    private final UserService userService;

    @Transactional(readOnly = true)
    public String createNewAccessToken(String refreshToken){
        if (!tokenProvider.validToken(refreshToken)){
            throw new TokenException(TokenError.INVALID_TOKEN);
        }
        Long userId = refreshTokenService.findByRefreshToken(refreshToken).getUserId();
        User user = userService.findById(userId);

        return tokenProvider.generateToken(user, Duration.ofHours(6));
    }
}
