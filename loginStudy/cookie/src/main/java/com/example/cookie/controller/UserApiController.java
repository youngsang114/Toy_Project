package com.example.cookie.controller;

import com.example.cookie.db.UserRepository;
import com.example.cookie.model.UserDto;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserApiController {

    private final UserRepository userRepository;
    @GetMapping("/me")
    public UserDto me(HttpServletRequest httpServletRequest,
                      @CookieValue(name = "authorization-cookie",required = false) String authorizationCookie){


        log.info("authorizationCookies : {}",authorizationCookie);
        Optional<UserDto> optionalUserDto = userRepository.findById(authorizationCookie);
        return optionalUserDto.get();

/*        Cookie[] cookies = httpServletRequest.getCookies();

        if (cookies !=null){
            for (Cookie cookie : cookies) {
                log.info("key : {}, value : {}" ,cookie.getName(),cookie.getValue());
            }
        }
        return null;*/
    }

    @GetMapping("/me2")
    public UserDto me2(@RequestHeader(name = "authorization", required = false) String authorizationHeader) {


        log.info("authorizationCookies : {}", authorizationHeader);
        Optional<UserDto> optionalUserDto = userRepository.findById(authorizationHeader);
        return optionalUserDto.get();
    }

}
