package com.vay.messenger.service;

import com.vay.messenger.jwt.AuthenticationRequest;
import com.vay.messenger.jwt.AuthenticationResponse;
import com.vay.messenger.jwt.RegisterRequest;
import com.vay.messenger.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder().build();
        return
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        return
    }
}
