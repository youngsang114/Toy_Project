package toyproject.board.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import toyproject.board.entity.BoardEntity;

import java.lang.reflect.Member;
import java.util.List;
import java.util.Optional;

@Repository
@Getter
@Slf4j
@RequiredArgsConstructor
public class BoardRepository {

    private final EntityManager em;

    public void save(BoardEntity boardEntity){
        em.persist(boardEntity);
    }

    public Optional<BoardEntity> findById(Long id){
        BoardEntity entity = em.find(BoardEntity.class, id);
        return Optional.ofNullable(entity);
    }

    public List<BoardEntity> findAll(){
        return em.createQuery("select b from BoardEntity b", BoardEntity.class).getResultList();
    }

    //update board_table set board_hits=board_hits +1 where id=?
    public void updateHits(Long id){
//        em.createQuery("update BoardEntity b set b.boardHits=b.boardHits +1 where b.id =:id", BoardEntity.class)
//                .setParameter("id", id)
//                .executeUpdate();

        Query query = em.createQuery("update BoardEntity b set b.boardHits = b.boardHits + 1 where b.id = :id")
                .setParameter("id", id);

        query.executeUpdate();
    }
}
