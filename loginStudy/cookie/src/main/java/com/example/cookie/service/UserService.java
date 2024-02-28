package com.example.cookie.service;

import com.example.cookie.db.UserRepository;
import com.example.cookie.model.LoginRequest;
import com.example.cookie.model.UserDto;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    //login logic
    public String login(LoginRequest loginRequest, HttpServletResponse response){

        String id = loginRequest.getId();
        String password = loginRequest.getPassword();

        Optional<UserDto> optionalUser = userRepository.findByName(id);

        if (optionalUser.isPresent()){
            UserDto userDto = optionalUser.get();

            if (userDto.getPassword().equals(password)){

                return userDto.getId();
                /*Cookie cookie = new Cookie("authorization-cookie",userDto.getId());
                cookie.setDomain("localhost");  // naver.com, daum.net, dev.xxx.com
                cookie.setPath("/");
                cookie.setHttpOnly(true);   // <-- 자바 스크립트로 조작 불가능
//                cookie.setSecure(true);  // <-- https에서만 사용되도록 설정
                cookie.setMaxAge(-1);  // session과 통일, 시간을 지정해줄 수 있음

                response.addCookie(cookie);*/
            }

        }else {
            throw new RuntimeException("User not found");
        }
        return null;
    }
}
