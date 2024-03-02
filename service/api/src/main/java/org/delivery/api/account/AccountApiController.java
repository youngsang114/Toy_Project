package org.delivery.api.account;

import lombok.RequiredArgsConstructor;
import org.delivery.api.account.model.AccountMeResponse;
import org.delivery.api.common.api.Api;
import org.delivery.api.common.error.ErrorCode;
import org.delivery.api.common.error.UserErrorCode;
import org.delivery.api.common.exception.ApiException;
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
    public Api<AccountMeResponse> me(){

        AccountMeResponse response = AccountMeResponse.builder()
                .name("홍길동")
                .email("A@naver.com")
                .registeredAt(LocalDateTime.now())
                .build();

        String str = "안녕하세요";
        try {
            int age = Integer.parseInt(str); // 예외 발생

        }catch (Exception e){
            throw new ApiException(ErrorCode.SERVER_ERROR, e, "사용자 me 호출시 에러 발생");
        }

        return Api.OK(response);
    }
}
