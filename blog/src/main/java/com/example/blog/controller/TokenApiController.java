package com.example.blog.controller;

import com.example.blog.common.api.Api;
import com.example.blog.dto.token.CreateAccessTokenRequest;
import com.example.blog.dto.token.CreateAccessTokenResponse;
import com.example.blog.service.TokenService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class TokenApiController {
    private final TokenService tokenService;

    @PostMapping("/api/token")
    public Api<CreateAccessTokenResponse> createAccessToken(
            @RequestBody CreateAccessTokenRequest request){
        String newAccessToken = tokenService.createNewAccessToken(request.getRefreshToken());

        return Api.OK(new CreateAccessTokenResponse(newAccessToken));
    }
}
