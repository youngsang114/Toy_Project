package hello.loginBoard.member.service;

import hello.loginBoard.member.domain.Member;
import hello.loginBoard.member.dto.LoginFormDTO;
import hello.loginBoard.member.dto.MemberSaveDTO;
import hello.loginBoard.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public void save(MemberSaveDTO memberSaveDTO){

        Member member = Member.builder()
                .loginId(memberSaveDTO.getLoginId())
                .password(memberSaveDTO.getPassword())
                .name(memberSaveDTO.getName())
                .build();

        memberRepository.save(member);
    }

    public Member login(LoginFormDTO loginFormDTO) {
        Member member = memberRepository.findByLoginid(loginFormDTO.getLoginId());
        return member.passwordCheck(loginFormDTO.getPassword());

    }
}
