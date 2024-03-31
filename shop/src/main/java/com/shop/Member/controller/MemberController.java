package com.shop.Member.controller;

import com.shop.Member.dto.request.JoinDto;
import com.shop.Member.dto.response.JoinResponse;
import com.shop.Member.service.MemberService;
import com.shop.common.api.Api;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    // 회원가입
    @PostMapping("/new")
    public Api<JoinResponse> saveMember(@Valid JoinDto joinDto){
        memberService.saveMember(joinDto);
        return Api.OK(JoinResponse
                .builder()
                .email(joinDto.getEmail())
                .name(joinDto.getName())
                .build());
    }
}
