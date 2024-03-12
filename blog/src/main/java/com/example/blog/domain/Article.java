package com.example.blog.domain;

import jakarta.persistence.*;
import lombok.*;
import org.apache.el.lang.ELArithmetic;

@Getter
@Entity
@Table(name="article")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Builder
public class Article {

    @Id @GeneratedValue
    @Column(name = "id",updatable = false)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;
}
