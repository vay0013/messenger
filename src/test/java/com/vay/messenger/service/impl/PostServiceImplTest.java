package com.vay.messenger.service.impl;

import com.vay.messenger.config.TestConfig;
import com.vay.messenger.domain.exception.ResourceNotFoundException;
import com.vay.messenger.domain.post.Post;
import com.vay.messenger.repository.PostRepository;
import com.vay.messenger.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@Import(TestConfig.class)
@ExtendWith(MockitoExtension.class)
public class PostServiceImplTest {

    @MockBean
    private PostRepository postRepository;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private AuthenticationManager authenticationManager;

    @Autowired
    private PostServiceImpl postService;

    @Test
    void getById() {
        Long id = 1L;
        Post post = new Post();
        post.setId(id);
        Mockito.when(postRepository.findById(id))
                .thenReturn(Optional.of(post));
        Post testPost = postService.getById(id);
        Mockito.verify(postRepository).findById(id);
        Assertions.assertEquals(post, testPost);

    }

    @Test
    void getByIdWithNotExistingId() {
        Long id = 1L;
        Mockito.when(postRepository.findById(id))
                .thenReturn(Optional.empty());
        Assertions.assertThrows(ResourceNotFoundException.class,
                () -> postService.getById(id));
        Mockito.verify(postRepository).findById(id);
    }

    @Test
    void getAllByUserId() {
        Long userId = 1L;
        List<Post> posts = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            posts.add(new Post());
        }
        Mockito.when(postRepository.findAllByUserId(userId))
                .thenReturn(posts);
        List<Post> testPosts = postService.getAllByUserId(userId);
        Mockito.verify(postRepository).findAllByUserId(userId);
        Assertions.assertEquals(posts, testPosts);
    }

    @Test
    void update() {
        Post post = new Post();
        post.setId(1L);
        post.setTitle("aboba");
        post.setDescription("aloha");
        post.setCreatedAt(LocalDateTime.now());
        Post testPost = postService.update(post);
        Mockito.verify(postRepository).save(post);
        Assertions.assertEquals(post, testPost);
    }

    @Test
    void create() {
        Long postId = 1L;
        Long userId = 1L;
        Post post = new Post();
        Mockito.doAnswer(invocationOnMock -> {
            Post savedPost = invocationOnMock.getArgument(0);
            savedPost.setId(postId);
            return savedPost;
        }).when(postRepository).save(post);

        Post testPost = postService.create(post, userId);
        Mockito.verify(postRepository).save(post);
        Assertions.assertNotNull(testPost.getId());
        Mockito.verify(postRepository).assignTask(userId, post.getId());
    }

    @Test
    void delete() {
        Long id = 1L;
        postService.delete(id);
        Mockito.verify(postRepository).deleteById(id);
    }
}