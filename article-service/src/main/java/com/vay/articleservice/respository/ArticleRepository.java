package com.vay.articleservice.respository;

import com.vay.articleservice.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}