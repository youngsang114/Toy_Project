package com.example.restapi.controller;

import com.example.restapi.model.BookQueryParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api")
public class RestApiController {
    @GetMapping(path = "/hello")
    public String hello(){
        return "Hello Spring Boot";
    }

    @GetMapping(path = "/echo/{message}/age/{age}/is-man/{isMan}")
    // http://localhost:8080/api/echo/Steve/age/25/is-man/true 실행
    public String echo(
            @PathVariable("message") String message,
            @PathVariable("age") int age, // Integer : null포함 가능, int : null 포함 불가능, 여기서는 null을 파라미터로 받으면 -> 404 error
            @PathVariable("isMan") boolean isMan
    ){
        log.info("echo message : "+message);
        log.info("echo age : "+age);
        log.info("echo isMan : "+isMan);
        return message.toUpperCase();
    }
    // http://localhost:8080/api/book?category=IT&issuedYear=2023&issued-month=01&issued_day=31
    @GetMapping(path="/book")
    public void queryParam(
        @RequestParam(name = "category") String category,
        @RequestParam(name="issuedYear") String issuedYear,
        @RequestParam(name = "issued-month") String issuedMonth,
        @RequestParam(name = "issued_day") String issuedDay
    ){
        log.info(category);
        log.info(issuedYear);
        log.info(issuedMonth);
        log.info(issuedDay);
    }
    // http://localhost:8080/api/book2?category=IT&issuedYear=2023&issued-month=01&issued_day=31
    // http://localhost:8080/api/book2?category=IT&issuedYear=2023&issuedMonth=01&issuedDay=31
    @GetMapping(path="/book2")
    public void queryParamDto(
            BookQueryParam bookQueryParam
    ){
        System.out.println(bookQueryParam);
    }

    // TODO parameter 2가지 받기 int 형태로 받아서 두 수의 덧셈, 곱셈
    // TODO String 타입, boolean 타입

    @GetMapping(path ="/int")
    public void plusTime(
            @RequestParam("a") int a,
            @RequestParam("b") int b
    ){
        log.info("a+b : "+a+b);
        log.info("a*b : "+a*b);

    }



}
