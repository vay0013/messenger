package com.vay.messenger.service.impl;

import com.vay.messenger.config.TestConfig;
import com.vay.messenger.domain.exception.ResourceNotFoundException;
import com.vay.messenger.domain.user.Role;
import com.vay.messenger.domain.user.User;
import com.vay.messenger.repository.PostRepository;
import com.vay.messenger.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
import java.util.Set;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@Import(TestConfig.class)
@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private PostRepository postRepository;

    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserServiceImpl userService;

    @Test
    void getById() {
        Long id = 1L;
        User user = new User();
        user.setId(id);
        Mockito.when(userRepository.findById(id))
                .thenReturn(Optional.of(user));
        User testUser = userService.getById(id);
        Mockito.verify(userRepository).findById(id);
        Assertions.assertEquals(user, testUser);
    }

    @Test
    void getByIdWithNotExistingId() {
        Long id = 1L;
        Mockito.when(userRepository.findById(id))
                .thenReturn(Optional.empty());
        Assertions.assertThrows(ResourceNotFoundException.class,
                () -> userService.getById(id));
        Mockito.verify(userRepository).findById(id);
    }

    @Test
    void getByEmail() {
        String email = "email";
        User user = new User();
        user.setEmail(email);
        Mockito.when(userRepository.findByEmail(email))
                .thenReturn(Optional.of(user));
        User testUser = userService.getByEmail(email);
        Mockito.verify(userRepository).findByEmail(email);
        Assertions.assertEquals(user, testUser);
    }

    @Test
    void getByEmailWithNotExistingEmail() {
        String email = "email";
        Mockito.when(userRepository.findByEmail(email))
                .thenReturn(Optional.empty());
        Assertions.assertThrows(ResourceNotFoundException.class,
                () -> userService.getByEmail(email));
        Mockito.verify(userRepository).findByEmail(email);
    }

    @Test
    void update() {
        String password = "password";
        User user = new User();
        user.setPassword(password);
        userService.update(user);
        Mockito.verify(passwordEncoder).encode(password);
        Mockito.verify(userRepository).save(user);
    }

    @Test
    void isPostOwner() {
        Long userId = 1L;
        Long postId = 1L;
        Mockito.when(userRepository.isPostOwner(userId, postId))
                .thenReturn(true);
        boolean isOwner = userService.isPostOwner(userId, postId);
        Mockito.verify(userRepository).isPostOwner(userId, postId);
        Assertions.assertTrue(isOwner);
    }

    @Test
    void create() {
        String email = "email";
        String password = "password";
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setPasswordConfirmation(password);
        User testUser = userService.create(user);
        Mockito.verify(userRepository).save(user);
        Mockito.verify(passwordEncoder).encode(password);
        Assertions.assertEquals(Set.of(Role.ROLE_USER), testUser.getRoles());
    }

    @Test
    void createWithDifferentPasswords() {
        String name = "name";
        String email = "email";
        String password = "password";
        String passwordConfirmation = "passwordConfirmation";
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setPasswordConfirmation(passwordConfirmation);
        Assertions.assertThrows(IllegalStateException.class,
                () -> userService.create(user));
        Mockito.verify(userRepository, Mockito.never()).save(user);
    }

    @Test
    void delete() {
        Long id = 1L;
        userService.delete(id);
        Mockito.verify(userRepository).deleteById(id);
    }

}