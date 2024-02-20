package toyproject.board.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import toyproject.board.dto.BoardDTO;

// DB의 테이블 역할을 하는 클래스
@Entity
@Getter @Setter
@Table(name = "board_table")
public class BoardEntity extends BaseEntity{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false)
    private String boardWriter;

    private String boardPass;

    private String boardTitle;

    @Column(length = 500)
    private String boardContents;

    private int boardHits;


    public static BoardEntity toSaveEntity(BoardDTO boardDTO){
        BoardEntity boardEntity = new BoardEntity();

        boardEntity.setBoardWriter(boardDTO.getBoardWriter());
        boardEntity.setBoardPass(boardDTO.getBoardPass());
        boardEntity.setBoardTitle(boardDTO.getBoardTitle());
        boardEntity.setBoardContents(boardDTO.getBoardContents());
        boardEntity.setBoardHits(0);
        return boardEntity;
    }

    public static BoardEntity toUpdateEntity(BoardDTO boardDTO) {
        BoardEntity boardEntity = new BoardEntity();

        boardEntity.setId(boardDTO.getId());
        boardEntity.setBoardWriter(boardDTO.getBoardWriter());
        boardEntity.setBoardPass(boardDTO.getBoardPass());
        boardEntity.setBoardTitle(boardDTO.getBoardTitle());
        boardEntity.setBoardContents(boardDTO.getBoardContents());
        boardEntity.setBoardHits(boardDTO.getBoardHits());

        return boardEntity;
    }

    public void toUpdateEntity2(BoardDTO boardDTO){

        this.boardWriter = boardDTO.getBoardWriter();
        this.boardPass = boardDTO.getBoardPass();
        this.boardTitle= boardDTO.getBoardTitle();
        this.boardContents = boardDTO.getBoardContents();
        this.boardHits = boardDTO.getBoardHits();

    }
}
