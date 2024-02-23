package hello.loginBoard.board.repository;

import hello.loginBoard.board.domain.BoardEntity;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class BoardRepository {

    private final EntityManager em;

    public void save(BoardEntity board){
        em.persist(board);
    }

    public BoardEntity findOne(Long id){
        return em.find(BoardEntity.class, id);
    }

    public List<BoardEntity> findAll(){
        String jpql = "SELECT b FROM BoardEntity b LEFT JOIN b.member";
        return em.createQuery(jpql, BoardEntity.class).getResultList();
    }
}
