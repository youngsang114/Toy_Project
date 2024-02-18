package toyproject.board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toyproject.board.dto.BoardDTO;
import toyproject.board.entity.BoardEntity;
import toyproject.board.repository.BoardRepository;

// DTO -> Entity
// Entity -> DTO

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {
    private final BoardRepository boardRepository;
    @Transactional(readOnly = false)
    public Long save(BoardDTO boardDTO) {
//        BoardEntity boardEntity = new BoardEntity(boardDTO);
        BoardEntity boardEntity = BoardEntity.toSaveEntity(boardDTO);
        boardRepository.save(boardEntity);
        return boardEntity.getId();
    }
}
