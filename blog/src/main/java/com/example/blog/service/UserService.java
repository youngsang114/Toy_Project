package com.example.blog.service;

import com.example.blog.common.error.ErrorCode;
import com.example.blog.common.error.UserError;
import com.example.blog.common.exception.ApiException;
import com.example.blog.domain.User;
import com.example.blog.dto.AddUserRequest;
import com.example.blog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Long save(AddUserRequest dto){
        return userRepository.save(User.builder()
                .email(dto.getEmail())
                .password(bCryptPasswordEncoder.encode(dto.getPassword()))
                .build()).getId();
    }

    public User findById(Long userId){
        return userRepository.findById(userId)
                .orElseThrow(() -> new ApiException(UserError.NOT_FOUND_USER_ID));
    }
}
