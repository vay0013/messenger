package com.vay.messenger.web.mapper;

import com.vay.messenger.domain.post.Post;
import com.vay.messenger.web.dto.post.PostDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostMapper extends Mappable<Post, PostDto>{
}
