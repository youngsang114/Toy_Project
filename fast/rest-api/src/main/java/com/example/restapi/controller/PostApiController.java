package com.example.restapi.controller;

import com.example.restapi.model.BookRequest;
import com.example.restapi.model.UserRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(path = "/api")
public class PostApiController {
    @PostMapping("/post")
    // http://localhost:8080/api/post
    public BookRequest post(
        @RequestBody BookRequest bookRequest
    ){
        log.info(bookRequest.toString());
        return bookRequest;
    }

    @PostMapping("/user")
    // http://localhost:8080/api/user
    public UserRequest user(
            @RequestBody UserRequest userRequest
    ){
        log.info(userRequest.toString());
        return userRequest;
    }

}
