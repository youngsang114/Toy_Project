package com.shop.common.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode implements ErrorCodeIfs{
    OK(200,"성공"),
    BAD_REQUEST(400,"잘못된 요청"),
    SERVER_ERROR(500,"서버 에러"),
    NULL_POINT(HttpStatus.INTERNAL_SERVER_ERROR.value(),"NullPointError");

    private final Integer httpStatusCode;
    private final String description;
}