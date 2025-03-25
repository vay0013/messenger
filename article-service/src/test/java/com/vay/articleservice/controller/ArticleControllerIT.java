package com.vay.articleservice.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
class ArticleControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Sql("/sql/articles.sql")
    void getArticles() throws Exception {
        var requestBuilder = MockMvcRequestBuilders.get("/api/v1");

        // when
        mockMvc.perform(requestBuilder)
                // then
                .andDo(print())
                .andExpectAll(
                        status().isOk(),
                        content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON),
                        content().json("""
                                [
                                    {
                                        "id": 1,
                                        "title": "c1",
                                        "content": "t1",
                                        "createdAt": "2025-03-25T12:14:22.97372"
                                    },
                                    {
                                        "id": 2,
                                        "title": "c2",
                                        "content": "t2",
                                        "createdAt": "2025-03-25T12:14:22.97372"
                                    },
                                    {
                                        "id": 3,
                                        "title": "c3",
                                        "content": "t3",
                                        "createdAt": "2025-03-25T12:14:22.97372"
                                    },
                                    {
                                        "id": 4,
                                        "title": "c4",
                                        "content": "t4",
                                        "createdAt": "2025-03-25T12:14:22.97372"
                                    }
                                ]""")
                );
    }

    @Test
    void getArticleById() {
    }

    @Test
    void createArticle() {
    }

    @Test
    void updateArticle() {
    }

    @Test
    void deleteArticle() {
    }
}