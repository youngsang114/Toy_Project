package com.example.jwt.service;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;

@Slf4j
@Service
public class JwtService {

    private static String secretKey = "java11SpringBootJWTTokenIssueMethod";

    public String create(
            Map<String, Object> claims,
            LocalDateTime expireAt
    ){

        SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes());
        var _expireAt = Date.from(expireAt.atZone(ZoneId.systemDefault()).toInstant());

        return Jwts.builder()
                .signWith(key, SignatureAlgorithm.HS256)
                .setClaims(claims)
                .setExpiration(_expireAt)
                .compact();

    }

    public void validation(String token){

        SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes());
        JwtParser parser = Jwts.parserBuilder()
                .setSigningKey(key)
                .build();

        try {
            Jws<Claims> result = parser.parseClaimsJws(token);
            result.getBody().entrySet().forEach(
                    value->log.info("key : {}, value : {}",value.getKey(),value.getValue())

            );

        }catch(Exception e){
            if (e instanceof SignatureException){
                throw new RuntimeException("JWT token validation Exception");


            } else if (e instanceof ExpiredJwtException) {
                throw new RuntimeException("JWT token expired Exception");

            }else {
                throw new RuntimeException("JWT token validation Exception");
            }
        }




    }
}
