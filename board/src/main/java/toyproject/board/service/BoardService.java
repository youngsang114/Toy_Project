package toyproject.board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toyproject.board.dto.BoardDTO;
import toyproject.board.entity.BoardEntity;
import toyproject.board.repository.BoardRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// DTO -> Entity
// Entity -> DTO

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {
    private final BoardRepository boardRepository;
    @Transactional(readOnly = false)
    public Long save(BoardDTO boardDTO) {
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
    public BoardDTO updateBoard(Long id, BoardDTO boardDTO) {
        Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(id);
        BoardEntity updateEntity = optionalBoardEntity.get().toUpdateEntity(boardDTO);
        return BoardDTO.toBoardDTO(updateEntity);
    }
    @Transactional(readOnly = false)
    public BoardDTO updateBoard2(Long id, BoardDTO boardDTO) {
        Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(id);

        BoardEntity boardEntity = optionalBoardEntity.orElseThrow(() -> new RuntimeException("게시물이 존재하지 않습니다. ID: " + id));
        boardEntity.toUpdateEntity2(boardDTO);

        return BoardDTO.toBoardDTO(boardEntity);
    }

    @Transactional(readOnly = false)
    public void delete(Long id) {
        boardRepository.deleteById(id);
    }

    public Page<BoardDTO> paging(Pageable pageable) {

        // Sort.by를 사용하여 정렬 기준을 지정 (예: id 역순 정렬)
        Sort sort = Sort.by(Sort.Direction.DESC, "id");

        // PageRequest.of 메서드를 사용하여 Pageable 객체를 생성하면서 정렬 정보를 전달
        Pageable sortedPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);

        Page<BoardEntity> boardEntityPage = boardRepository.findAllPaging(sortedPageable);

        System.out.println("boardEntities.getContent() = " + boardEntityPage.getContent()); // 요청 페이지에 해당하는 글
        System.out.println("boardEntities.getTotalElements() = " + boardEntityPage.getTotalElements()); // 전체 글갯수
        System.out.println("boardEntities.getNumber() = " + boardEntityPage.getNumber()); // DB로 요청한 페이지 번호
        System.out.println("boardEntities.getTotalPages() = " + boardEntityPage.getTotalPages()); // 전체 페이지 갯수
        System.out.println("boardEntities.getSize() = " + boardEntityPage.getSize()); // 한 페이지에 보여지는 글 갯수
        System.out.println("boardEntities.hasPrevious() = " + boardEntityPage.hasPrevious()); // 이전 페이지 존재 여부
        System.out.println("boardEntities.isFirst() = " + boardEntityPage.isFirst()); // 첫 페이지 여부
        System.out.println("boardEntities.isLast() = " + boardEntityPage.isLast()); // 마지막 페이지 여부

        // 목록: id, writer, title, hits, createdTime
        Page<BoardDTO> boardDTOS = boardEntityPage.map(board -> new BoardDTO(board.getId(), board.getBoardWriter(), board.getBoardTitle(), board.getBoardHits(), board.getCreatedTime()));
        return boardDTOS;
    }


}
