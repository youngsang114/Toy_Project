package toyproject.board.repository;

import jakarta.persistence.EntityManager;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import toyproject.board.entity.BoardEntity;

import java.lang.reflect.Member;
import java.util.List;

@Repository
@Getter
@Slf4j
@RequiredArgsConstructor
public class BoardRepository {

    private final EntityManager em;

    public void save(BoardEntity boardEntity){
        em.persist(boardEntity);
    }

    public BoardEntity findOne(Long id){
        return em.find(BoardEntity.class,id);
    }

    public List<BoardEntity> findAll(){
        return em.createQuery("select b from BoardEntity b", BoardEntity.class).getResultList();
    }
}
