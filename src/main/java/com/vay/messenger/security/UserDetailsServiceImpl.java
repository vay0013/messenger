package com.vay.messenger.security;

import com.vay.messenger.domain.user.User;
import com.vay.messenger.domain.user.UserDetailsImpl;
import com.vay.messenger.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(username).orElseThrow(() ->
            new UsernameNotFoundException(String.format("User %s not found", username))
        );
        return UserDetailsImpl.build(user);
    }
}
