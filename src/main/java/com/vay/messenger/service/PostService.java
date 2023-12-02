package com.vay.messenger.service;

import com.vay.messenger.domain.post.Post;
import com.vay.messenger.web.dto.post.PostDto;

import java.util.List;

public interface PostService {
    Post create(Post post, Long userId);

    Post update(Post post);

    Post getById(Long id);

    List<Post> getAllByUserId(Long userId);

    void delete(Long id);
}
