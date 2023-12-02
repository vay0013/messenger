package com.vay.messenger.web.mapper;

import com.vay.messenger.domain.user.User;
import com.vay.messenger.web.dto.user.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends Mappable<User, UserDto>{
}
