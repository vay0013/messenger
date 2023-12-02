package com.vay.messenger.web.controller;

import com.vay.messenger.domain.post.Post;
import com.vay.messenger.domain.user.User;
import com.vay.messenger.service.PostService;
import com.vay.messenger.service.UserService;
import com.vay.messenger.web.dto.post.PostDto;
import com.vay.messenger.web.dto.user.UserDto;
import com.vay.messenger.web.mapper.PostMapper;
import com.vay.messenger.web.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final PostService postService;

    private final UserMapper userMapper;
    private final PostMapper postMapper;

    @PutMapping
    public UserDto update(@RequestBody UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        userService.update(user);
        return userMapper.toDto(user);
    }

    @GetMapping("/{id}")
    public UserDto getById(@PathVariable Long id) {
        User user = userService.getById(id);
        return userMapper.toDto(user);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        userService.delete(id);
    }

    @GetMapping("/{id}/posts")
    public List<PostDto> getPostsByUserId(@PathVariable Long id) {
        List<Post> posts = postService.getAllByUserId(id);
        return postMapper.toDto(posts);
    }

    @PutMapping("/{id}/posts")
    public PostDto createPost(@PathVariable Long id, @RequestBody PostDto dto) {
        Post post = postMapper.toEntity(dto);
        Post createdPost = postService.create(post, id);
        return postMapper.toDto(createdPost);
    }

}





























