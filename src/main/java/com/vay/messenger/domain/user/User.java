package com.vay.messenger.domain.user;


import com.vay.messenger.domain.post.Post;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String password;

    @Transient
    private String passwordConfirmation;

    @ElementCollection
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "users_roles")
    private Set<Role> roles;

    @OneToMany
    @CollectionTable(name = "users_tasks")
    @JoinColumn(name = "post_id")
    List<Post> posts;
}
