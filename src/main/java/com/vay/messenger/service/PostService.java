package com.vay.messenger.service;

import com.vay.messenger.dto.PostDto;
import com.vay.messenger.exception.NotFoundException;
import com.vay.messenger.mapper.PostMapper;
import com.vay.messenger.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final PostMapper postMapper;

    public void save(PostDto dto) {
        postRepository.save(postMapper.toEntity(dto));
    }

    public PostDto findPostById(Long id) {
        return postMapper.toDto(postRepository.findById(id).orElseThrow(() ->
                        new NotFoundException("post not found")
                ));
    }

    public List<PostDto> findPosts() {
        return postMapper.toDto(postRepository.findAll());
    }

    public void delete(Long id) {
        postRepository.deleteById(id);
    }
}
