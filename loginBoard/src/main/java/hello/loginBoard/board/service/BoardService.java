package hello.loginBoard.board.service;

import hello.loginBoard.board.domain.BoardEntity;
import hello.loginBoard.board.dto.BoardDTO;
import hello.loginBoard.board.repository.BoardRepository;
import hello.loginBoard.login.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {

    private final BoardRepository boardRepository;

    public List<BoardDTO> findAll(){
        List<BoardEntity> boardEntityList = boardRepository.findAll();

        List<BoardDTO> boardDTOList =new ArrayList<>();

        for (BoardEntity boardEntity:boardEntityList){
            BoardDTO boardDTO = BoardDTO.toBoardDTO(boardEntity);
            boardDTOList.add(boardDTO);

        }
        return boardDTOList;
    }

    @Transactional(readOnly = false)
    public void save(BoardDTO boardDTO, Member member) {

        BoardEntity boardEntity = BoardEntity.builder()
                .boardTitle(boardDTO.getBoardTitle())
                .boardContents(boardDTO.getBoardContents())
                .boardHits(0)
                .member(member)
                .build();
        boardRepository.save(boardEntity);
    }
    @Transactional(readOnly = false)
    public void updateHits(Long id) {
        boardRepository.updateHits(id);
    }

    public BoardDTO findById(Long id) {
        return boardRepository.findById(id)
                .map((boardEntity) -> BoardDTO.toBoardDTO(boardEntity))
                .orElseThrow(() -> new RuntimeException());

    }

    @Transactional(readOnly = false)
    public BoardDTO updateBoard(Long id, BoardDTO boardDTO) {
        Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(id);

        BoardEntity boardEntity = optionalBoardEntity.orElseThrow(() -> new RuntimeException());
        boardEntity.updateEntity(boardDTO);

        return BoardDTO.toBoardDTO(boardEntity);
    }

    @Transactional(readOnly = false)
    public void delete(Long id) {
        boardRepository.delete(id);
    }
}
