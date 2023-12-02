package com.vay.messenger.repository;

import com.vay.messenger.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String username);

    @Query(value = """
                    SELECT exists(
                        SELECT 1
                        FROM users_posts
                        WHERE user_id = :userId
                            AND post_id = :postId)
            """, nativeQuery = true)
    boolean isPostOwner(@Param("userId") Long userId, @Param("postId") Long postId);

}
