package com.vay.messenger.config;

import com.vay.messenger.repository.PostRepository;
import com.vay.messenger.repository.UserRepository;
import com.vay.messenger.service.AuthService;
import com.vay.messenger.service.PostService;
import com.vay.messenger.service.UserService;
import com.vay.messenger.service.impl.AuthServiceImpl;
import com.vay.messenger.service.impl.PostServiceImpl;
import com.vay.messenger.service.impl.UserServiceImpl;
import com.vay.messenger.service.props.JwtProperties;
import com.vay.messenger.web.security.JwtTokenProvider;
import com.vay.messenger.web.security.JwtUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@TestConfiguration
@RequiredArgsConstructor
public class TestConfig {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final AuthenticationManager authenticationManager;

    @Bean
    @Primary
    public PasswordEncoder testPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtProperties jwtProperties() {
        JwtProperties jwtProperties = new JwtProperties();
        jwtProperties.setSecret("a2xzZGphaGZia2xzZGpiZnNkamhhZml1c2RqYWl1ZGhmYnNuDQo=");
        return jwtProperties;
    }

    @Bean
    @Primary
    public UserDetailsService userDetailsService() {
        return new JwtUserDetailsService(userService());
    }

    @Bean
    public JwtTokenProvider tokenProvider() {
        return new JwtTokenProvider(jwtProperties(), userDetailsService(), userService());
    }

    @Bean
    @Primary
    public UserService userService() {
        return new UserServiceImpl(userRepository, testPasswordEncoder());
    }

    @Bean
    @Primary
    public PostService postService() {
        return new PostServiceImpl(postRepository, userService());
    }

    @Bean
    @Primary
    public AuthService authService() {
        return new AuthServiceImpl(authenticationManager, userService(), tokenProvider());
    }

}
