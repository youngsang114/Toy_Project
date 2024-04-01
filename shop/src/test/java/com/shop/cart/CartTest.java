package com.shop.cart;

import com.shop.Member.dto.request.JoinDto;
import com.shop.Member.entity.Member;
import com.shop.Member.repository.MemberRepository;
import com.shop.cart.entity.Cart;
import com.shop.cart.repository.CartRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application.yml")
public class CartTest {

    @Autowired
    CartRepository cartRepository;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @PersistenceContext
    EntityManager em;

    @Test
    @DisplayName("장바구니 회원 엔티티 매핑 조회 테스트")
    public void findCartAndMemberTest(){
        Member member = Member.createMember(JoinDto.builder()
                .email("test@naver.com")
                .name("홍길동")
                .address("seoul")
                .password("1234")
                .build(), bCryptPasswordEncoder);
        memberRepository.save(member);

        Cart cart = Cart.builder()
                .member(member)
                .build();

        cartRepository.save(cart);

        em.flush();
        em.clear();

        Cart savedCart = cartRepository.findById(cart.getId()).orElseThrow(() -> new EntityNotFoundException());
        Assertions.assertThat(savedCart.getMember().getId()).isEqualTo(member.getId());

    }
}
