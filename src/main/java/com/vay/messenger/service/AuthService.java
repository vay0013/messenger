package com.vay.messenger.service;

import com.vay.messenger.web.dto.auth.JwtRequest;
import com.vay.messenger.web.dto.auth.JwtResponse;

public interface AuthService {
    JwtResponse login(JwtRequest loginRequest);
    JwtResponse refresh(String refreshToken);
}
