package com.vay.messenger.Controller;

import com.vay.messenger.dto.PostDto;
import com.vay.messenger.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/")
public class PostController {
    private final PostService postService;

    @GetMapping("{id}")
    public PostDto getPost(@PathVariable Long id) {
        return postService.findPostById(id);
    }

    @PostMapping("/create")
    public void createPost(PostDto dto) {
        postService.save(dto);
    }

    @GetMapping
    public List<PostDto> getAllPosts() {
        return postService.findPosts();
    }

    @DeleteMapping("{id}")
    public void deletePost(@PathVariable Long id) {
        postService.delete(id);
    }
}
