package hello.loginBoard.board.repository;

import hello.loginBoard.board.domain.BoardEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class BoardRepository {

    private final EntityManager em;

    public void save(BoardEntity board){
        em.persist(board);
    }
    public Optional<BoardEntity> findById(Long id) {
        BoardEntity boardEntity = em.find(BoardEntity.class, id);
        return Optional.ofNullable(boardEntity);
    }


    public List<BoardEntity> findAll(){
        String jpql = "SELECT b FROM BoardEntity b LEFT JOIN b.member";
        return em.createQuery(jpql, BoardEntity.class).getResultList();
    }

    public void updateHits(Long id) {
        String jpql = "update BoardEntity b set b.boardHits = b.boardHits+1 where b.id=:id";
        Query query = em.createQuery(jpql).setParameter("id", id);
        query.executeUpdate();
    }


}
