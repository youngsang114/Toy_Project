package toyproject.board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toyproject.board.dto.BoardDTO;
import toyproject.board.entity.BoardEntity;
import toyproject.board.repository.BoardRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public List<BoardDTO> findAll() {
        // db에서는 entity로 넘어온다
        List<BoardEntity> boardEntityList = boardRepository.findAll();

        // Entity객체를 -> DTO로 변환해서 컨트롤러로 넘겨줘야 한다
        List<BoardDTO> boardDTOList = new ArrayList<>();

        for (BoardEntity boardEntity:boardEntityList){
            boardDTOList.add(BoardDTO.toBoardDTO(boardEntity));
        }

        return boardDTOList;
    }
    @Transactional(readOnly = false)
    public void updateHits(Long id) {
        boardRepository.updateHits(id);
    }

    public BoardDTO findById(Long id) {
//        Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(id);
//        if (optionalBoardEntity.isPresent()){
//            BoardEntity boardEntity = optionalBoardEntity.get();
//            return BoardDTO.toBoardDTO(boardEntity);
//        } else {
//            return null;
//        }
        return boardRepository.findById(id)
                .map((boardEntity) -> BoardDTO.toBoardDTO(boardEntity))
                .orElseThrow(() ->  new RuntimeException("게시물이 존재하지 않습니다. ID: " + id));
    }

    @Transactional(readOnly = false)
    public BoardDTO update(BoardDTO boardDTO) {
        BoardEntity boardEntity=BoardEntity.toUpdateEntity(boardDTO);
        boardRepository.save(boardEntity);
        return findById(boardDTO.getId());
    }

//    @Transactional(readOnly = false)
//    public BoardDTO updateBoard(Long id, BoardDTO boardDTO) {
//        Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(id);
//        BoardEntity updateEntity = optionalBoardEntity.get().toUpdateEntity(boardDTO);
//    }
}
