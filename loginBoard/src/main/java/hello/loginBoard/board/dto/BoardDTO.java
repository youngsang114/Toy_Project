package hello.loginBoard.board.dto;

import hello.loginBoard.member.domain.Member;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor // 기본생성자
@AllArgsConstructor // 모든 필드를 매개변수로 하는 생성자
public class BoardDTO {
    private Long id;
    private String boardTitle;
    private String boardContents;
    private int boardHits;
    private LocalDateTime boardCreatedTime;
    private Member member;

    public BoardDTO saveForm(){
        return BoardDTO.builder()
                .boardTitle(this.boardTitle)
                .boardContents(this.boardContents)
                .build();
    }
}
