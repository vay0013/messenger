package com.vay.messenger.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.vay.messenger.domain.user.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String username);
}
