package com.shop.config.jwt;

import com.shop.common.error.TokenErrorCode;
import com.shop.common.exception.TokenException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SignatureException;
import java.util.Date;

@Component
public class JwtUtil {
    private final SecretKey secretKey;
    public JwtUtil(@Value("${spring.jwt.secret-key}") String secret){
        this.secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), Jwts.SIG.HS256.key().build().getAlgorithm());
    }
    // 토큰을 생성 하는 메서드
    public String createJwt(String category, String username, String role, Long expiredMs){
        return Jwts.builder()
                .claim("category" ,category)
                .claim("username", username)
                .claim("role",role)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiredMs))
                .signWith(secretKey)
                .compact();
    }

    public boolean validToken(String token){

        try{
            makeParser()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e){
            if (e instanceof SignatureException){
                throw new TokenException(TokenErrorCode.INVALID_TOKEN);
            }
            else if (e instanceof ExpiredJwtException) {
                // 만료된 토큰
                throw new TokenException(TokenErrorCode.EXPIRED_AT,e);
            }
            else {
                // 그 외 처리
                throw new TokenException(TokenErrorCode.INVALID_TOKEN,e);
            }
        }
    }

    //전달 받은 토큰에서 payLoad의 claims에서 username(email)을 얻기
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
    public String getCategory(String token){
        JwtParser parser = makeParser();

        return parser.parseSignedClaims(token)
                .getPayload()
                .get("category", String.class);
    }

    // 전달 받은 토큰이 만료되었는지 확인 -> Bool 반환
    public Boolean isExpired(String token){
        JwtParser parser = makeParser();

        return parser.parseSignedClaims(token)
                .getPayload()
                .getExpiration()
                .before(new Date());
    }
    //  parser을 이용해서, jwt token 검증 메서드

    private JwtParser makeParser() {
        return Jwts.parser().verifyWith(secretKey).build();
    }
}
