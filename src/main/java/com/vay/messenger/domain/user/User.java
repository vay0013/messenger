package com.vay.messenger.domain.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    private Long id;

    private String username;

    private String email;

    private String password;

    @Transient
    private String passwordConfirmation;

    @Enumerated(value = EnumType.STRING)
    private Set<Role> roles;
}
