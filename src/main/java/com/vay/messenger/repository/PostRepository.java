package com.vay.messenger.repository;

import com.vay.messenger.domain.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    @Query(value = """
            SELECT * FROM posts p
            JOIN users_posts up on up.post_id = p.id
            WHERE up.user_id = :userId
            """, nativeQuery = true)
    List<Post> findAllByUserId(@Param("userId") Long userId);

    @Modifying
    @Query(value = """
            INSERT INTO users_posts (user_id, post_id)
            VALUES (:userId, :postId)
            """, nativeQuery = true)
    void assignTask(@Param("userId") Long userId, @Param("postId") Long postId);

}
