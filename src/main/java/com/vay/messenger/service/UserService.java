package com.vay.messenger.service;

import com.vay.messenger.domain.user.User;
import com.vay.messenger.domain.user.UserDetailsImpl;
import com.vay.messenger.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username).orElseThrow(() ->
            new UsernameNotFoundException(String.format("User %s not found", username))
        );
        return UserDetailsImpl.build(user);
    }
}
