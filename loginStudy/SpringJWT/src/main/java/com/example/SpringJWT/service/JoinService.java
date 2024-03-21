package com.example.SpringJWT.service;

import com.example.SpringJWT.common.error.UserError;
import com.example.SpringJWT.common.exception.ApiException;
import com.example.SpringJWT.dto.JoinDTO;
import com.example.SpringJWT.entity.UserEntity;
import com.example.SpringJWT.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class JoinService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    public UserEntity joinProcess(JoinDTO joinDTO){
        UserEntity findByEmail = userRepository.findByEmail(joinDTO.getEmail());
        if (findByEmail!=null){
            throw new ApiException(UserError.DUPLICATE_USER_EMAIL);
        }
        UserEntity saveUser = UserEntity.builder()
                .email(joinDTO.getEmail())
                .password(bCryptPasswordEncoder.encode(joinDTO.getPassword()))
                .role("ROLE_ADMIN")
                .build();

        return userRepository.save(saveUser);
    }
}
