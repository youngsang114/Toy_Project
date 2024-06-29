package com.study.SecurityJWT.common.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserError implements ErrorCodeIfs{

    NOT_FOUND_USER(500,2502,"not found user by email"),
    NOT_FOUND_USER_ID(500,2503,"not found user by id"),
    DUPLICATE_USER_EMAIL(500, 2504, "duplicate email, change email");

    private final Integer httpStatusCode;
    private final Integer errorCode;
    private final String description;
}
