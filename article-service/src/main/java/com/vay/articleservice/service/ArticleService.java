package com.vay.articleservice.service;

import com.vay.articleservice.model.Article;

import java.util.List;

public interface ArticleService {
    Article findById(long id);

    List<Article> findAll();

    Article create(String title, String content);

    void update(long id, String title, String content);

    void delete(long id);
}
