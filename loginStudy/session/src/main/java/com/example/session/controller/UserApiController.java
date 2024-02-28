package com.example.session.controller;

import com.example.session.model.UserDto;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserApiController {
    @GetMapping("/me")
    public UserDto me(HttpSession httpSession){

        UserDto user= (UserDto) httpSession.getAttribute("USER");
        if (user != null){
            return user;
        }else {
            return null;
        }

    }
}
