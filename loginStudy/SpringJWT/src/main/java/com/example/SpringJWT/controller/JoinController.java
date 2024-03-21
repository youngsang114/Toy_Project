package com.example.SpringJWT.controller;
import com.example.SpringJWT.common.api.Api;
import com.example.SpringJWT.dto.JoinDTO;
import com.example.SpringJWT.dto.JoinResponse;
import com.example.SpringJWT.entity.UserEntity;
import com.example.SpringJWT.service.JoinService;
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

        return Api.OK(JoinResponse
                .builder()
                .email(userEntity.getEmail())
                .build());

    }
}
