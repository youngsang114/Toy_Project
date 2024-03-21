package com.example.SpringJWT.exceptionhandler;

import com.example.SpringJWT.common.api.Api;
import com.example.SpringJWT.common.error.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@Order(value = Integer.MAX_VALUE)
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Api<Object>> exception(Exception e){

        log.error("",e);
        return ResponseEntity
                .status(500)
                .body(
                        Api.Error(ErrorCode.SERVER_ERROR)
                );
    }
}
