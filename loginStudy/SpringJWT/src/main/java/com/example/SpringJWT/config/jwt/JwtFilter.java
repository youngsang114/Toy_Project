package com.example.SpringJWT.config.jwt;

import com.example.SpringJWT.common.error.TokenErrorCode;
import com.example.SpringJWT.common.exception.TokenException;
import com.example.SpringJWT.dto.CustomUserDetails;
import com.example.SpringJWT.entity.UserEntity;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@RequiredArgsConstructor
@Slf4j
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final static String HEADER_AUTHORIZATION = "Authorization";
    private final static String TOKEN_PREFIX = "Bearer ";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // request에서 Authorization 헤더를 찾기
        String authorization = request.getHeader(HEADER_AUTHORIZATION);

        // Authorization 헤더 검증 -> jwt token인지
        if (authorization == null || !authorization.startsWith("Bearer ")){
            log.info("token null");
            filterChain.doFilter(request,response);
            // 다음 조건이 해당하면 -> 메서드 종료
            return;
        }

        // 가져온 값에서 접두사 제거 -> 토큰 꺼내 오기
        String token = getAccessToken(authorization);

        // 토큰 소멸 시간 검증
        if (jwtUtil.isExpired(token)){
            log.info("token expired");
            throw new TokenException(TokenErrorCode.EXPIRED_AT);
//            filterChain.doFilter(request,response);
        }

        // 토큰에서 username, role 획득
        String email = jwtUtil.getUsername(token);
        String role = jwtUtil.getRole(token);

        // userEntity를 생성해서 해당 값을 넣어준다
        UserEntity userEntity = UserEntity.builder()
                .email(email)
                .role(role)
                .password("temppassword")
                .build();

        //UserDetails에 회원 정보 객체 담기
        CustomUserDetails customUserDetails = new CustomUserDetails(userEntity);

        //스프링 시큐리티 인증 토큰 생성
        Authentication authToken = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());

        //세션에 사용자 등록
        SecurityContextHolder.getContext().setAuthentication(authToken);

        filterChain.doFilter(request, response);
    }

    private String getAccessToken(String authorization){
        if (authorization != null && authorization.startsWith(TOKEN_PREFIX)){
            return authorization.substring(TOKEN_PREFIX.length());
        }
        return null;
    }
}
