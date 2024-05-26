package com.example.simple_board.board.service;

import com.example.simple_board.board.db.BoardEntity;
import com.example.simple_board.board.db.BoardRepository;
import com.example.simple_board.board.model.BoardDto;
import com.example.simple_board.board.model.BoardRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final BoardConverter boardConverter;

    /**
     * 게시판인 BoardEntity를 만드는 create 메서드
     * @param boardRequest
     * @return boardEntity
     */
    public BoardDto create(
            BoardRequest boardRequest
    ){
        BoardEntity registered = BoardEntity.builder()
                .boardName(boardRequest.getBoardName())
                .status("REGISTERED")
                .build();

        BoardEntity saveEntity = boardRepository.save(registered);
        return boardConverter.toDto(saveEntity);
    }

    public BoardDto view(Long id) {
        BoardEntity boardEntity = boardRepository.findById(id).get();
        return boardConverter.toDto(boardEntity);
    }
}
