package com.vay.messenger.Controller;

import com.vay.messenger.dto.PostDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PostController {
    @GetMapping
    public List<PostDto> getAllPosts() {
        return null;
    }
}
