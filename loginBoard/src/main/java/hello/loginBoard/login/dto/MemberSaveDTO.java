package hello.loginBoard.member.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;

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
