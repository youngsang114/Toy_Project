package com.example.simple_board.jpapractice;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "Team")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "team")
    List<Member> members = new ArrayList<>();
}
