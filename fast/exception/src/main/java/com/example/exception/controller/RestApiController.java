package com.example.exception.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@Slf4j
@RestController
@RequestMapping(path = "/api")
public class RestApiController {

    @GetMapping(path = "")
    public void hello(){
        List<String> list = List.of("hello");

        String element = list.get(1);
        log.info("element : {}",element);
    }
}
