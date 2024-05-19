package com.example.restapi.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
@RequestMapping("/api")
public class RestApiController {
    @GetMapping(path = "/hello")
    public String hello(){
        return "Hello Spring Boot";
    }

    @GetMapping(path = "/echo/{message}")
    public String echo(
            @PathVariable("message") String message
    ){
        log.info("echo message : "+message);
        return message.toUpperCase();
    }
}
