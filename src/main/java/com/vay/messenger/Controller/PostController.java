package com.vay.messenger.Controller;

import com.vay.messenger.dto.PostDto;
import com.vay.messenger.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping
    public PostDto getPost(Long id) {
        return postService.findPostById(id);
    }

    @PostMapping
    public void createPost(PostDto dto) {
        postService.save(dto);
    }

    @GetMapping
    public List<PostDto> getAllPosts() {
        return postService.findPosts();
    }

    @DeleteMapping
    public void deletePost(Long id) {
        postService.delete(id);
    }
}
