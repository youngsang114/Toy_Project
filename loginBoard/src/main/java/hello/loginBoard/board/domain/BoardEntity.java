package hello.loginBoard.board.domain;

import hello.loginBoard.board.dto.BoardDTO;
import hello.loginBoard.login.domain.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "board_table")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardEntity extends BaseEntity{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private String boardTitle;

    @Column(length = 2000)
    private String boardContents;

    private int boardHits;

    public void updateEntity(BoardDTO boardDTO) {
        this.boardTitle= boardDTO.getBoardTitle();
        this.boardContents= boardDTO.getBoardContents();
    }
}
