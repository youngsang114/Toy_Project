package com.example.simple_board.board.db;

import com.example.simple_board.post.db.PostEntity;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Where;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity(name = "board")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class BoardEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String boardName;

    private String status;

    @OneToMany(mappedBy = "board")
    @Where(clause = "status = 'REGISTERED'")
    @Builder.Default
    @OrderBy("id desc")
    private List<PostEntity> postList = new ArrayList<>();

}
