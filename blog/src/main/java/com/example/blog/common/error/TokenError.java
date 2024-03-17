package com.example.blog.common.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TokenError implements ErrorCodeIfs {

    INVALID_TOKEN(400,2000,"유효하지 않은 토큰"),
    EXPIRED_AT(400,2001,"만료된 토큰"),
    TOKEN_EXCEPTION(400,2002,"토큰의 알 수 없는 에러입니다"),
    AUTHORIZATION_TOKEN_NOT_FOUND(400, 2003, "인증 헤더 토큰이 없습니다");

    private final Integer httpStatusCode; // 상응하는 http status 코드
    private final Integer errorCode;  // 인터널 에러 코드
    private final String description; //설명
}
