package com.example.blog.dto;

import com.example.blog.domain.Article;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ArticleResponse {

    private final String title;
    private final String content;

}
