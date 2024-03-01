package org.delivery.api.account;

import lombok.RequiredArgsConstructor;
import org.delivery.api.account.model.AccountMeResponse;
import org.delivery.api.common.api.Api;
import org.delivery.api.common.error.UserErrorCode;
import org.delivery.db.account.AccountRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/account")
public class AccountApiController {

    private final AccountRepository accountRepository;

    @GetMapping("/me")
    public Api<Object> me(){

        AccountMeResponse response = AccountMeResponse.builder()
                .name("홍길동")
                .email("A@naver.com")
                .registeredAt(LocalDateTime.now())
                .build();

        return Api.ERROR(UserErrorCode.USER_NOT_FOUND,"홍길동이라는 사용자 없음");
    }
}
