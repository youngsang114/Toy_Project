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

//    protected BoardEntity(){}
//    public BoardEntity(BoardDTO boardDTO){
//        this.boardWriter = boardDTO.getBoardWriter();
//        this.boardPass=boardDTO.getBoardPass();
//        this.boardTitle=boardDTO.getBoardTitle();
//        this.boardContents=boardDTO.getBoardContents();
//        this.boardHits=0;
//    }

    public static BoardEntity toSaveEntity(BoardDTO boardDTO){
        BoardEntity boardEntity = new BoardEntity();

        boardEntity.setBoardWriter(boardEntity.getBoardWriter());
        boardEntity.setBoardPass(boardEntity.getBoardPass());
        boardEntity.setBoardTitle(boardEntity.getBoardTitle());
        boardEntity.setBoardContents(boardEntity.getBoardContents());
        boardEntity.setBoardHits(0);
        return boardEntity;
    }

}
