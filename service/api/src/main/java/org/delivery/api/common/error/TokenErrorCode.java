package org.delivery.api.common.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Token의  경우 2000번대 에러 코드 사용
 */

@AllArgsConstructor
@Getter
public enum TokenErrorCode implements ErrorCodeIfs {

    INVALID_TOKEN(400,2000,"유효하지 않은 토큰입니다"),
    EXPIRED_TOKEN(400,2001,"만료된 토큰입니다"),
    TOKEN_EXCEPTION(400,2002,"트큰 -> 알 수 없는 에러입니다"),
    AUTHORIZATION_TOKEN_NOT_FOUND(400,2003,"인증 헤더 토큰이 없습니다");

    private final Integer httpStatusCode; // 상응하는 http status 코드
    private final Integer errorCode;  // 인터널 에러 코드
    private final String description; //설명

}
