package com.study.SecurityJWT.controller;

import com.study.SecurityJWT.common.api.Api;
import com.study.SecurityJWT.dto.JoinDTO;
import com.study.SecurityJWT.dto.JoinResponse;
import com.study.SecurityJWT.entity.UserEntity;
import com.study.SecurityJWT.service.JoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class JoinController {

    private final JoinService joinService;

    @PostMapping("/join")
    public Api<JoinResponse> joinProcess(JoinDTO joinDTO){
        UserEntity userEntity = joinService.joinProcess(joinDTO);
        JoinResponse response = JoinResponse.builder()
                .email(userEntity.getEmail())
                .build();
        return Api.OK(response);
    }
}
