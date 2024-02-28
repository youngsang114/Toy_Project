package com.example.session.service;

import com.example.session.db.UserRepository;
import com.example.session.model.LoginRequest;
import com.example.session.model.UserDto;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void login(LoginRequest loginRequest, HttpSession httpSession){

        String id = loginRequest.getId();
        String password = loginRequest.getPassword();

        Optional<UserDto> optionalUser = userRepository.findByName(id);

        if (optionalUser.isPresent()){
            UserDto userDto = optionalUser.get();
            if (userDto.getPassword().equals(password)){
                // 세션에 정보 저장 -> 서버에서 관리되는 정보
                httpSession.setAttribute("USER",userDto);
            }else {
                throw new RuntimeException("Password not match");
            }
        } else {
            // 없는 유저
            throw new RuntimeException("User not found");
        }

    }
}
