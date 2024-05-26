package com.example.simple_board.post.db;

import com.example.simple_board.board.db.BoardEntity;
import com.example.simple_board.reply.db.ReplyEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity(name = "post")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PostEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnore
    @ToString.Exclude
    private BoardEntity board;

    private String userName;

    private String password;

    private String email;

    private String status;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime postedAt;

    @OneToMany(
            mappedBy = "post"
    )
    // 댓글을 반환하기 위한 필드 추가
    private List<ReplyEntity> replyList = List.of();

}
