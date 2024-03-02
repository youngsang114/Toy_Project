package hello.loginBoard.login.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberSaveDTO {

    @NotBlank
    private String loginId;

    @NotBlank(message = "비밀번호는 입력해야 합니다")
    private String password;

    @NotBlank(message = "이름은 입력해야 합니다")
    private String name;
}
