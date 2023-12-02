package com.vay.messenger.service.impl;

import com.vay.messenger.domain.exception.ResourceNotFoundException;
import com.vay.messenger.domain.post.Post;
import com.vay.messenger.domain.user.User;
import com.vay.messenger.repository.PostRepository;
import com.vay.messenger.service.PostService;
import com.vay.messenger.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UserService userService;

    @Override
    @Transactional
    public Post create(Post post, Long userId) {
        User user = userService.getById(userId);
        user.getPosts().add(post);
        userService.update(user);
        return post;
    }

    @Override
    @Transactional
    public Post update(Post post) {
        postRepository.save(post);
        return post;
    }

    @Override
    @Transactional(readOnly = true)
    public Post getById(Long id) {
        return postRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Exception while finding post by id")
        );
    }

    @Override
    @Transactional(readOnly = true)
    public List<Post> getAllByUserId(Long userId) {
        return postRepository.findAllByUserId(userId);
    }

    @Transactional
    public void delete(Long id) {
        postRepository.deleteById(id);
    }
}
