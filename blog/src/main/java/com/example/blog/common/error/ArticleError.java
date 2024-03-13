package com.example.blog.common.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ArticleError implements ErrorCodeIfs{

    NOT_FOUND_ID(500,1502,"not found article_id");

    private final Integer httpStatusCode;
    private final Integer errorCode;
    private final String description;
}
