package com.example.exception.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
@RequestMapping("/api/b")
public class RestApiBController {
    //http://localhost:8080/api/b/hello
    @GetMapping("/hello")
    public void hello(){
        throw new NumberFormatException("number format exception");
    }
// 예외를 RestControllerAdvice 말고, 부분적으로 특정한 컨트롤러에서 처리하는 방법
//    @ExceptionHandler(value = {NumberFormatException.class})
//    public ResponseEntity numberFormatException(
//            NumberFormatException e
//    ){
//        log.error("RestApiBController",e);
//        return ResponseEntity.ok().build();
//    }
}
