package com.example.blog.controller;

import com.example.blog.common.api.Api;
import com.example.blog.common.error.ArticleError;
import com.example.blog.common.exception.ApiException;
import com.example.blog.domain.Article;
import com.example.blog.dto.ArticleRegisterRequest;
import com.example.blog.dto.ArticleResponse;
import com.example.blog.dto.UpdateArticleRequest;
import com.example.blog.service.BlogService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class BlogApiController {

    private final BlogService blogService;

    @PostMapping("/articles")
    public Api<Article> addArticle(@Valid @RequestBody ArticleRegisterRequest request){
        Article article = blogService.save(request);
        return Api.OK(article);
    }

    @GetMapping("/articles")
    public Api<List<ArticleResponse>> findAllArticles(){
        List<ArticleResponse> articleResponses = blogService.findAll().stream()
                .map(it -> {
                            return ArticleResponse.builder()
                                    .title(it.getTitle())
                                    .content(it.getContent())
                                    .build();
                        }
                ).collect(Collectors.toList());
        return Api.OK(articleResponses);
    }

    @GetMapping("/articles/{id}")
    public Api<Article> findArticle(@PathVariable("id") Long id){
        Article article = blogService.findById(id);
        return Api.OK(article);
    }

    @DeleteMapping("/articles/{id}")
    public Api<Object> deleteArticle(@PathVariable("id") Long id){
        blogService.delete(id);
        return Api.OK_DELETE();
    }

    @PutMapping("/articles/{id}")
    public Api<Article> updateArticle(@PathVariable("id") Long id, @RequestBody UpdateArticleRequest request){
        Article updatedArticle = blogService.update(id, request);
        return Api.OK(updatedArticle);
    }
}
