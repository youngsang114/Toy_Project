package com.example.blog.exceptionhandler;

import com.example.blog.common.api.Api;
import com.example.blog.common.error.ErrorCodeIfs;
import com.example.blog.common.exception.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@Order(value = Integer.MIN_VALUE)
public class ApiExceptionHandler {

    @ExceptionHandler(value = ApiException.class)
    public ResponseEntity<Api<Object>> apiException(ApiException apiException){

        log.error("", apiException);

        ErrorCodeIfs errorCodeIfs = apiException.getErrorCodeIfs();

        return ResponseEntity
                .status(errorCodeIfs.getHttpStatusCode())
                .body(
                        Api.Error(errorCodeIfs, apiException.getErrorDescription())
                );
    }
}
