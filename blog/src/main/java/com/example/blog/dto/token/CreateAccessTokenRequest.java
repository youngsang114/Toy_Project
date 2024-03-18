package com.example.blog.dto.token;

import lombok.Data;

@Data
public class CreateAccessTokenRequest {
    private String refreshToken;
}
