package com.example.blog.service;

import com.example.blog.common.error.TokenError;
import com.example.blog.common.exception.TokenException;
import com.example.blog.domain.RefreshToken;
import com.example.blog.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.Token;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshToken findByRefreshToken(String refreshToken) {
        return refreshTokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new TokenException(TokenError.REFRESH_TOKEN_EXCEPTION));
    }
}
