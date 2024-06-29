package com.study.SecurityJWT.service;

import com.study.SecurityJWT.dto.CustomUserDetails;
import com.study.SecurityJWT.entity.UserEntity;
import com.study.SecurityJWT.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userData = userRepository.findByEmail(email);

        if (userData != null){
            return new CustomUserDetails(userData);
        }

        return null;
    }
}
