package com.example.blog.common.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserError implements ErrorCodeIfs{

    NOT_FOUND_USER(500,2502,"not found user by email");

    private final Integer httpStatusCode;
    private final Integer errorCode;
    private final String description;
}
