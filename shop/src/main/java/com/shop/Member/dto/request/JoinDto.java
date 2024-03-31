package com.shop.Member.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class JoinDto {
    @NotBlank(message = "이름은 필수 입력값입니다")
    private String name;

    @Email
    @NotBlank(message ="email은 필수 입력값입니다")
    private String email;

    @NotBlank(message = "비밀번호는 필수 입력값입니다")
    private String password;

    @NotBlank(message = "주소는 필수 입력값입니다")
    private String address;
}
