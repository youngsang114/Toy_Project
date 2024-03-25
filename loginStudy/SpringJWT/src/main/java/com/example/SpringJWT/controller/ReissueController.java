package com.example.SpringJWT.controller;

import com.example.SpringJWT.common.api.Api;
import com.example.SpringJWT.common.error.TokenErrorCode;
import com.example.SpringJWT.common.exception.TokenException;
import com.example.SpringJWT.config.jwt.JwtUtil;
import com.example.SpringJWT.entity.RefreshEntity;
import com.example.SpringJWT.repository.RefreshRepository;
import com.example.SpringJWT.service.RefreshTokenService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequiredArgsConstructor
public class ReissueController {

    private final JwtUtil jwtUtil;
    private final RefreshTokenService refreshTokenService;
    private final RefreshRepository refreshRepository;
    @PostMapping("/reissue")
    public Api<?> reissue(HttpServletRequest request, HttpServletResponse response){

        String refresh = refreshTokenService.findRefreshCookie(request);

        if (refresh == null) {

            return Api.Error(TokenErrorCode.NULL_REFRESH_TOKEN);

        }
        //expired check
        try {
            jwtUtil.isExpired(refresh);
        } catch (TokenException e) {

            //response status code
            return Api.Error(TokenErrorCode.EXPIRED_REFRESH_TOKEN);
        }

        // 토큰이 refresh인지 확인 (발급시 페이로드에 명시)
        String category = jwtUtil.getCategory(refresh);

        if (!category.equals("refresh")) {

            //response status code
            return Api.Error(TokenErrorCode.REFRESH_TOKEN_EXCEPTION);
        }

        // DB에 저장되어 있는지 확인
        Boolean isExist = refreshRepository.existsByRefresh(refresh);
        if (!isExist) {

            //response body
            return Api.Error(TokenErrorCode.INVALID_REFRESH_TOKEN);
        }

        String username = jwtUtil.getUsername(refresh);
        String role = jwtUtil.getRole(refresh);

        //make new JWT
        String newAccess = jwtUtil.createJwt("access", username, role, 600000L);
        String newRefresh = jwtUtil.createJwt("refresh", username, role, 86400000L);

        //Refresh 토큰 저장 DB에 기존의 Refresh 토큰 삭제 후 새 Refresh 토큰 저장
        refreshRepository.deleteByRefresh(refresh);
        addRefreshEntity(username, newRefresh, 86400000L);


        //response
        response.addHeader("Authorization","Bearer " + newAccess);
        response.addCookie(createCookie("refresh", newRefresh));

        return Api.OK(null);
    }
    private void addRefreshEntity(String email, String refresh, Long expiredMs) {

        Date date = new Date(System.currentTimeMillis() + expiredMs);

        RefreshEntity refreshEntity = RefreshEntity.builder()
                .email(email)
                .refresh(refresh)
                .expiration(date.toString())
                .build();
        refreshRepository.save(refreshEntity);
    }
    private Cookie createCookie(String key, String value) {

        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(24*60*60);
        //cookie.setSecure(true);
        //cookie.setPath("/");
        cookie.setHttpOnly(true);

        return cookie;
    }
}
