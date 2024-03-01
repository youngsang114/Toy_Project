package org.delivery.api.common.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * User의 경우 1000번대 에러 코드 사용
 */

@AllArgsConstructor
@Getter
public enum UserErrorCode implements ErrorCodeIfs {

    USER_NOT_FOUND(400,1404,"사용자를 찾을 수 없습니다");

    private final Integer httpStatusCode; // 상응하는 http status 코드
    private final Integer errorCode;  // 인터널 에러 코드
    private final String description; //설명

}
