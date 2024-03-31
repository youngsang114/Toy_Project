package com.shop.Member.service;

import com.shop.Member.dto.request.JoinDto;
import com.shop.common.error.MemberError;
import com.shop.common.exception.CustomException;
import com.shop.Member.entity.Member;
import com.shop.Member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Member saveMember(JoinDto joinDto){
        Member member = Member.createMember(joinDto,bCryptPasswordEncoder);
        validDuplicateEmail(member);
        return memberRepository.save(member);
    }

    private void validDuplicateEmail(Member member) {
        Member findMember = memberRepository.findByEmail(member.getEmail());
        if (findMember != null){
            throw new CustomException(MemberError.DUPLICATE_USER_EMAIL);
        }
    }
}
