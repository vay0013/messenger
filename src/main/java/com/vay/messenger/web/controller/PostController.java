package com.vay.messenger.web.controller;

import com.vay.messenger.domain.post.Post;
import com.vay.messenger.service.PostService;
import com.vay.messenger.web.dto.post.PostDto;
import com.vay.messenger.web.mapper.PostMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/posts")
public class PostController {

    private final PostService postService;
    private final PostMapper postMapper;

    @GetMapping("{id}")
    public Post getPostById(@PathVariable Long id) {
        Post post = postService.getById(id);
        return post;
    }

    @PutMapping
    public PostDto updatePost(@RequestBody PostDto postDto) {
        Post post = postMapper.toEntity(postDto);
        postService.update(post);
        return postMapper.toDto(post);
    }


    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Long id) {
        postService.delete(id);
    }
}
