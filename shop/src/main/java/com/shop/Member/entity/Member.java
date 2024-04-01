package com.shop.Member.entity;

import com.shop.Member.dto.request.JoinDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@Table(name = "member")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    private String address;

    private String role;

    public static Member createMember(JoinDto memberJoinDto, PasswordEncoder encoder){
        return Member.builder()
                .name(memberJoinDto.getName())
                .email(memberJoinDto.getEmail())
                .password(encoder.encode(memberJoinDto.getPassword()))
                .address(memberJoinDto.getAddress())
                .role("ROLE_ADMIN")
                .build();
    }
}
