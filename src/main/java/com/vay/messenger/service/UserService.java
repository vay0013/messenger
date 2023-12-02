package com.vay.messenger.service;

import com.vay.messenger.domain.user.User;
import com.vay.messenger.web.dto.user.UserDto;

public interface UserService {
    User getById(Long id);

    User getByEmail(String name);

    User update(User user);

    User create(User user);

    boolean isPostOwner(Long userId, Long taskId);

    void delete(Long id);
}
