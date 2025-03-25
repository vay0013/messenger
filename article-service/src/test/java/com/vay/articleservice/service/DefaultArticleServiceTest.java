package com.vay.articleservice.service;

import com.vay.articleservice.exception.ArticleNotFoundException;
import com.vay.articleservice.model.Article;
import com.vay.articleservice.respository.ArticleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DefaultArticleServiceTest {
    @Mock
    private ArticleRepository articleRepository;

    @InjectMocks
    private DefaultArticleService defaultArticleService;

    @Test
    void findById_whenSuccess_shouldReturnArticle() {
        // given
        var now = LocalDateTime.now();
        var article = new Article(1L, "title", "content", now);
        when(articleRepository.findById(1L)).thenReturn(Optional.of(article));

        // when
        var actualArticle = defaultArticleService.findById(1L);

        // then
        assertThat(actualArticle).isNotNull();
        assertThat(actualArticle.getId()).isEqualTo(1L);
        assertThat(actualArticle.getTitle()).isEqualTo("title");
        assertThat(actualArticle.getContent()).isEqualTo("content");
        assertThat(actualArticle.getCreatedAt()).isEqualTo(now);
        verify(articleRepository).findById(1L);
    }

    @Test
    void findById_whenNotFound_shouldThrowException() {
        // given & when
        var result = assertThrows(ArticleNotFoundException.class, () -> defaultArticleService.findById(1L));

        // then
        assertThat(result).isInstanceOf(ArticleNotFoundException.class);
        assertThat(result).hasMessage("Article with id: 1 not found");
        verify(articleRepository).findById(1L);
    }

    @Test
    void findAll_shouldReturnArticles() {
        // given
        var now = LocalDateTime.now();
        var articles = List.of(
                new Article(1L, "title", "content", now),
                new Article(2L, "title", "content", now),
                new Article(3L, "title", "content", now)
        );
        when(articleRepository.findAll()).thenReturn(articles);

        // when
        var actualArticles = defaultArticleService.findAll();

        // then
        assertThat(actualArticles).isNotNull();
        assertThat(actualArticles.size()).isEqualTo(3);
        verify(articleRepository).findAll();
    }

    @Test
    void create_shouldReturnArticle() {
        // given
        var title = "title";
        var content = "content";
        var now = LocalDateTime.now();

        var article = new Article(1L, title, content, now);
        when(articleRepository.save(any(Article.class))).thenReturn(article);

        // when
        var actualResult = defaultArticleService.create(title, content);

        // then
        assertThat(actualResult).isNotNull();
        assertThat(actualResult.getId()).isEqualTo(1L);
        assertThat(actualResult.getTitle()).isEqualTo(title);
        assertThat(actualResult.getContent()).isEqualTo(content);
        assertThat(actualResult.getCreatedAt()).isEqualTo(now);

        verify(articleRepository).save(any(Article.class));
    }

    @Test
    void update_whenSuccess_shouldUpdateArticle() {
        // given
        var id = 1L;
        var oldTitle = "Old Title";
        var oldContent = "Old Content";
        var existingArticle = new Article(id, oldTitle, oldContent, LocalDateTime.now());

        var newTitle = "New Title";
        var newContent = "New Content";

        when(articleRepository.findById(id)).thenReturn(Optional.of(existingArticle));

        // when
        defaultArticleService.update(id, newTitle, newContent);

        // then
        var articleCaptor = ArgumentCaptor.forClass(Article.class);
        verify(articleRepository).save(articleCaptor.capture());

        var updatedArticle = articleCaptor.getValue();
        assertThat(updatedArticle.getTitle()).isEqualTo(existingArticle.getTitle());
        assertThat(updatedArticle.getContent()).isEqualTo(existingArticle.getContent());
        assertThat(updatedArticle.getCreatedAt()).isEqualTo(existingArticle.getCreatedAt());

        verify(articleRepository).findById(id);
        verify(articleRepository).save(any(Article.class));
    }

    @Test
    void update_whenArticleDoesNotExist_shouldThrowException() {
        // given
        var id = 1L;
        var title = "Title";
        var content = "Content";

        when(articleRepository.findById(id)).thenReturn(Optional.empty());

        // when & then
        assertThatThrownBy(() -> defaultArticleService.update(id, title, content))
                .isInstanceOf(ArticleNotFoundException.class)
                .hasMessage("Article with id: %d not found".formatted(id));

        verify(articleRepository).findById(id);
        verify(articleRepository, never()).save(any());
    }

    @Test
    void delete_shouldCallRepositoryWithCorrectId() {
        // given
        var id = 1L;

        // when
        defaultArticleService.delete(id);

        // then
        verify(articleRepository, times(1)).deleteById(id);
    }
}