package hello.loginBoard.member.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "member_table")
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String loginId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    public Member passwordCheck(String password) {
        if (this.password.equals(password)) {
            return this;
        } else {
            throw new RuntimeException("Password does not match");
        }
    }
}
