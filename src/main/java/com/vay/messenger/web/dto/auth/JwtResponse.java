package com.vay.messenger.web.dto.auth;

import lombok.Data;

@Data
public class JwtResponse {
    private Long id;

    private String email;

    private String accessToken;

    private String refreshToken;
}
