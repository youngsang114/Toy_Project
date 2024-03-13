package com.example.blog.service;

import com.example.blog.common.error.ArticleError;
import com.example.blog.common.exception.ApiException;
import com.example.blog.domain.Article;
import com.example.blog.dto.ArticleRegisterRequest;
import com.example.blog.dto.UpdateArticleRequest;
import com.example.blog.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BlogService {

    private final BlogRepository blogRepository;

    // 단건 Article 저장
    public Article save(ArticleRegisterRequest request){
        return blogRepository.save(request.toEntity());
    }

    // List<Article> 찾기
    public List<Article> findAll(){
        return blogRepository.findAll();
    }

    // id를 이용한 Article 찾기
    public Article findById(Long id){
        return blogRepository.findById(id).orElseThrow(() -> new ApiException(ArticleError.NOT_FOUND_ID));
    }

    // Article 삭제
    @Transactional
    public void delete(Long id){
        Article article = blogRepository.findById(id).orElseThrow(() -> new ApiException(ArticleError.NOT_FOUND_ID));
        blogRepository.deleteById(article.getId());
    }

    // Article update
    @Transactional
    public Article update(Long id, UpdateArticleRequest request){
        Article article = blogRepository.findById(id).orElseThrow(() -> new ApiException(ArticleError.NOT_FOUND_ID));
        article.update(request.getTitle(),request.getContent());
        return article;
    }
}
