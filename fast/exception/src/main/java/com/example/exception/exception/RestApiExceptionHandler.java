package com.example.exception.exception;

import com.example.exception.controller.RestApiBController;
import com.example.exception.model.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@Slf4j
@Order(value = 1)
//@RestControllerAdvice(basePackages = "com.example.exception.controller")
// 이 클래스는 Rest Api가 사용하는 곳에 예외가 일어나는 것을 감지 하게 된다
@RestControllerAdvice()
public class RestApiExceptionHandler {
//    @ExceptionHandler(value = {Exception.class})
//    public ResponseEntity exception(
//            Exception e
//    ){
//        log.error("RestApiExceptionHandler",e);
//        return ResponseEntity.status(200).build();
//    }
    @ExceptionHandler(value = {IndexOutOfBoundsException.class})
    public ResponseEntity outOfBound(
            IndexOutOfBoundsException e
    ){
        log.error("IndexOutOfBoundsException",e);
        return ResponseEntity.status(200).build();
    }
    @ExceptionHandler(value = NoSuchElementException.class)
    public ResponseEntity<Api> noSuchElement(
            NoSuchElementException e
    ){
        log.error("",e);

        Api<Object> response = Api.builder()
                .resultCode(String.valueOf(HttpStatus.NOT_FOUND.value()))
                .resultMessage(HttpStatus.NOT_FOUND.getReasonPhrase())
                .build();
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(response);
    }
}
