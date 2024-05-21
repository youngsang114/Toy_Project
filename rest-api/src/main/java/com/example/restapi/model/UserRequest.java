package com.example.restapi.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class) // 헤당 클래스의 변수들을 snake_case로 매핑하겠다!
public class UserRequest {
    private String userName;
    private Integer userAge;
    private String email;
    private Boolean isKorean; // is_korean, boolean의 기본값 false
                              // primitive type에서 is 시리지는 boolean을 듯하기 때문에 setIsKorean이 아닌 SetKorean으로 만들어진다
                              // 1. 요청시 korean으로 요청한다(json에 전송하는 의미를 전달 못한다)
                              // 2. wrapper 클래스인 Boolean으로 고치자
}
