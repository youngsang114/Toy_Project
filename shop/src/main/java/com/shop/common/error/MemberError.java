package com.shop.common.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MemberError implements ErrorCodeIfs{

    NOT_FOUND_USER(500,"not found user by email"),
    NOT_FOUND_USER_ID(500,"not found user by id"),
    DUPLICATE_USER_EMAIL(400,"duplicate email, change email");

    private final Integer httpStatusCode;
    private final String description;
}
