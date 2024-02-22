package hello.loginBoard.member.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginFormDTO {
    @NotBlank(message = "로그인시 아이디 입력은 필수입니다.")
    private String loginId;

    @NotNull(message = "로그인시 비밀번호 입력은 필수입니다")
    private String password;
}
