package com.vay.messenger.controller;

import com.vay.messenger.jwt.AuthenticationRequest;
import com.vay.messenger.jwt.AuthenticationResponse;
import com.vay.messenger.jwt.RegisterRequest;
import com.vay.messenger.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/auth")
public class UserController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public AuthenticationResponse register(
            @RequestBody RegisterRequest request
    ) {
        return authenticationService.register(request);
    }

    @PostMapping("/authenticate")
    public AuthenticationResponse register(
            @RequestBody AuthenticationRequest request
    ) {
        return authenticationService.authenticate(request);
    }
}
