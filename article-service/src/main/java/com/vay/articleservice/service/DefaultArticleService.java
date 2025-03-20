package com.vay.articleservice.service;

import com.vay.articleservice.exception.ArticleNotFoundException;
import com.vay.articleservice.model.Article;
import com.vay.articleservice.respository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultArticleService implements ArticleService {
    private final ArticleRepository articleRepository;

    @Override
    public Article findById(long id) {
        return articleRepository.findById(id)
                .orElseThrow(() ->
                        new ArticleNotFoundException("Article with id: %d not found".formatted(id)));
    }

    @Override
    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    @Override
    public Article create(String title, String content) {
        Article article = new Article(null, title, content, LocalDateTime.now());
        return articleRepository.save(article);
    }

    @Override
    public void update(long id, String title, String content) {
        articleRepository.findById(id).ifPresent(article -> {
            article.setTitle(title);
            article.setContent(content);
            article.setCreatedAt(LocalDateTime.now());
            articleRepository.save(article);
        });
    }

    @Override
    public void delete(long id) {
        articleRepository.deleteById(id);
    }
}
