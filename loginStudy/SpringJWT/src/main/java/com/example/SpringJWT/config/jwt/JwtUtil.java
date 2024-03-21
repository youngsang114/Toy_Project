package com.example.SpringJWT.config.jwt;

import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtil {
    private final SecretKey secretKey ;
    public JwtUtil(@Value("${spring.jwt.secret-key}") String secret){
        this.secretKey  =new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), Jwts.SIG.HS256.key().build().getAlgorithm());
    }

    // 전달 받은 토큰에서 payLoad의 claims에서 username(email)을 얻기
    public String getUsername(String token){
        JwtParser parser = makeParser();

        return parser.parseSignedClaims(token)
                .getPayload()
                .get("username", String.class);
    }
    // 전달 받은 토큰에서 payLoad의 claims에서 role을 얻기
    public String getRole(String token){
        JwtParser parser = makeParser();

        return parser.parseSignedClaims(token)
                .getPayload()
                .get("role", String.class);
    }

    // 전달 받은 토큰이 만료되었는지 확인 -> Bool 반환
    public Boolean isExpired(String token){
        JwtParser parser = makeParser();

        return parser.parseSignedClaims(token)
                .getPayload()
                .getExpiration()
                .before(new Date());
    }

    // 토큰을 생성 하는 메서드
    public String createJwt(String username, String role, Long expiredMs){
        return Jwts.builder()
                .claim("username", username)
                .claim("role",role)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiredMs))
                .signWith(secretKey)
                .compact();
    }

    private JwtParser makeParser() {
        return Jwts.parser().verifyWith(secretKey).build();
    }

}
