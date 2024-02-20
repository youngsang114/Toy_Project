package toyproject.board.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import toyproject.board.dto.BoardDTO;
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

    public void deleteById(Long id) {
        Optional<BoardEntity> optionalBoardEntity = findById(id);
        optionalBoardEntity.ifPresent(boardEntity -> em.remove(boardEntity));
    }

    public Page<BoardEntity> findAllPaging(Pageable pageable) {
        int page = pageable.getPageNumber() - 1;
        int pageLimit = 3; // 한 페이지에 보여줄 글 개수

        List<BoardEntity> resultList = em.createQuery("select b from BoardEntity b", BoardEntity.class)
                .setFirstResult(page * pageLimit)
                .setMaxResults(pageLimit)
                .getResultList();

        // 전체 게시물 수 조회
        Long total = em.createQuery("select count(b) from BoardEntity b", Long.class).getSingleResult();

        return new PageImpl<>(resultList, PageRequest.of(page, pageLimit), total);
    }
}
