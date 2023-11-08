package com.vay.messenger.mapper;

import com.vay.messenger.domain.post.Post;
import com.vay.messenger.dto.PostDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostMapper extends Mappable<Post, PostDto>{
}
