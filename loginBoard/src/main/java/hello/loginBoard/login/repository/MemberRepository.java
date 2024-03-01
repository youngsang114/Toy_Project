package hello.loginBoard.member.repository;

import hello.loginBoard.member.domain.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;


@Repository
@Slf4j
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    public void save(Member member) {
        em.persist(member);
    }

    public Member findByLoginid(String loginId) {
        String jpql = "select m from Member m where m.loginId = :loginId";
        try {
            return em.createQuery(jpql, Member.class)
                    .setParameter("loginId", loginId)
                    .getSingleResult();
        } catch (NoResultException e) {
            throw new RuntimeException("Member not found");
        }
    }
}
