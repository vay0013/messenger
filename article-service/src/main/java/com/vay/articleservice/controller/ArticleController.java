package com.vay.articleservice.controller;

import com.vay.articleservice.dto.CreateArticleDto;
import com.vay.articleservice.dto.UpdateArticleDto;
import com.vay.articleservice.model.Article;
import com.vay.articleservice.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ArticleController {
    private final ArticleService articleService;

    @GetMapping
    public List<Article> getArticles() {
        return articleService.findAll();
    }

    @PostMapping
    public Article createArticle(@RequestBody(required = false) CreateArticleDto article) {
        return articleService.create(article.title(), article.content());
    }

    @PutMapping
    public void updateArticle(
            @RequestBody UpdateArticleDto updateArticleDto
            ) {
        articleService.update(updateArticleDto.id(), updateArticleDto.title(), updateArticleDto.content());
    }

    @DeleteMapping
    public void deleteArticle(@RequestBody Long id) {
        articleService.delete(id);
    }
}
