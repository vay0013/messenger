package com.vay.messenger.web.security;

import com.vay.messenger.domain.user.Role;
import com.vay.messenger.domain.user.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JwtEntityFactory {
    public static JwtEntity create(User user) {
        return new JwtEntity(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                mapToGrantedAuthority(new ArrayList<>(user.getRoles()))
        );
    }

    public static List<GrantedAuthority> mapToGrantedAuthority(List<Role> roles) {
        return roles.stream()
                .map(Enum::name)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

}
