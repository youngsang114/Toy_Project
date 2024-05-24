package com.example.validation.model;

import annotation.PhoneNumber;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserRegisterRequest {

//    @NotNull  // != null
//    @NotEmpty // != null && name != ""
//    @NotBlank // != null && name != "" && name != "   "
    private String name;

    private String nickName;

    @NotBlank
    @Size(min = 1, max = 12)
    private String password;

    @NotNull
    @Min(1)
    @Max(120)
    private Integer age;

    @Email
    private String email;

    @PhoneNumber
//    @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$", message = "휴대폰 번호 양식과 맞지 않습니다")
    private String phoneNumber;

    @FutureOrPresent // 현재 or 미래
    private LocalDateTime registerAt;
    @AssertTrue(message = "name or nickName 중 반듯이 1개가 존재해야 합니다") // 해당 리턴값이 true일 때 실행하는 어노테이션, 반듯이 is라는 메서드에 붙여줘야 한다
    public boolean isNameCheck(){
        if (Objects.nonNull(name) && !name.isBlank()){
            return true;
        }
        if (Objects.nonNull(nickName) && !nickName.isBlank()){
            return true;
        }
        return false;
    }
}
