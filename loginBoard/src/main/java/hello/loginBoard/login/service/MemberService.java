package hello.loginBoard.login.service;

import hello.loginBoard.login.domain.Member;
import hello.loginBoard.login.dto.LoginFormDTO;
import hello.loginBoard.login.dto.MemberSaveDTO;
import hello.loginBoard.login.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
