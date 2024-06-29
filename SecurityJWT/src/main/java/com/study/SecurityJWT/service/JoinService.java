package com.study.SecurityJWT.service;

import com.study.SecurityJWT.common.error.UserError;
import com.study.SecurityJWT.common.exception.ApiException;
import com.study.SecurityJWT.dto.JoinDTO;
import com.study.SecurityJWT.dto.JoinResponse;
import com.study.SecurityJWT.entity.UserEntity;
import com.study.SecurityJWT.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JoinService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserEntity joinProcess(JoinDTO joinDTO) {

        if (userRepository.existsByEmail(joinDTO.getEmail())) {
            throw new ApiException(UserError.DUPLICATE_USER_EMAIL);
        }
        UserEntity roleAdmin = UserEntity.builder()
                .email(joinDTO.getEmail())
                .password(bCryptPasswordEncoder.encode(joinDTO.getPassword()))
                .role("ROLE_ADMIN")
                .build();

        return userRepository.save(roleAdmin);
    }
}
