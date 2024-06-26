package com.example.blog.controller;

import com.example.blog.common.api.Api;
import com.example.blog.domain.Article;
import com.example.blog.dto.ArticleRegisterRequest;
import com.example.blog.dto.UpdateArticleRequest;
import com.example.blog.repository.BlogRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BlogApiControllerTest {

    @Autowired protected MockMvc mockMvc;
    @Autowired protected ObjectMapper objectMapper;
    @Autowired private WebApplicationContext context;
    @Autowired BlogRepository blogRepository;

    @BeforeEach
    public void mockMvcSetUp(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        blogRepository.deleteAll();
    }

    @Test
    @DisplayName("addArticle : 블로그 글 추가하기")
    public void addArticle() throws Exception {
        //given
        final String url ="/api/articles";
        final String title ="title";
        final String content ="content";
        final ArticleRegisterRequest request = new ArticleRegisterRequest(title,content);

        // 객체 json으로 직렬화
        final String requestBody = objectMapper.writeValueAsString(request);

        //when
        ResultActions result = mockMvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody));

        // then
        result.andExpect(status().isOk());
        List<Article> articles = blogRepository.findAll();
        assertThat(articles.size()).isEqualTo(1);
        assertThat(articles.get(0).getTitle()).isEqualTo(title);
        assertThat(articles.get(0).getContent()).isEqualTo(content);

    }

    @Test
    @DisplayName("findAllArticles : 블로그 글 목록 조회")
    public void findAllArticles() throws Exception {
        //given
        final String url ="/api/articles";
        final String title ="title";
        final String content ="content";

        blogRepository.save(Article.builder()
                .title(title)
                .content(content)
                .build());

        //when
        ResultActions resultActions = mockMvc.perform(get(url)
                .accept(MediaType.APPLICATION_JSON));

        // then
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.body[0].content").value(content))
                .andExpect(jsonPath("$.body[0].title").value(title));

    }

    @Test
    @DisplayName("findArticle : pk를 이용한 블로그 글 조회")
    public void findArticle() throws Exception {
        //given
        final String url = "/api/articles/{id}";
        final String title="title";
        final String content="content";

        Article savedArticle = blogRepository.save(Article.builder()
                .title(title)
                .content(content)
                .build());

        //when
        ResultActions resultActions = mockMvc.perform(get(url, savedArticle.getId()));

        // then

        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.body.content").value(content))
                .andExpect(jsonPath("$.body.title").value(title));

    }

    @Test
    @DisplayName("deleteArticle : 블로그 글 삭제")
    public void deleteArticle() throws Exception{

        //given
        final String url = "/api/articles/{id}";
        final String title="title";
        final String content="content";

        Article savedArticle = blogRepository.save(Article.builder()
                .title(title)
                .content(content)
                .build());

        // when
        ResultActions resultActions = mockMvc.perform(delete(url, savedArticle.getId()));

        // then
        resultActions.andExpect(status().isOk());
        assertThat(blogRepository.findAll()).isEmpty();
    }

    @Test
    @DisplayName("updateArticle : 블로그 글 수정")
    public void updateArticle() throws Exception{
        //given
        final String url = "/api/articles/{id}";
        final String title="title";
        final String content="content";

        Article savedArticle = blogRepository.save(Article.builder()
                .title(title)
                .content(content)
                .build());

        final String newTitle="new title";
        final String newContent="new content";

        UpdateArticleRequest updateArticleRequest = new UpdateArticleRequest(newTitle, newContent);

        //when

        ResultActions resultActions = mockMvc.perform(put(url, savedArticle.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateArticleRequest))
        );

        // then

        resultActions.andExpect(status().isOk());

        Article article = blogRepository.findById(savedArticle.getId()).get();

        assertThat(article.getTitle()).isEqualTo(newTitle);
        assertThat(article.getContent()).isEqualTo(newContent);
    }

}