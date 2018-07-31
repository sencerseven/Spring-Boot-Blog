package com.sencerseven.blog.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
public class UsersDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private String profileImg;

    private String firstName;

    private String lastName;

    private String email;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "usersDetail",fetch = FetchType.LAZY)
    private Set<Comment> comment = new HashSet<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    Users users;
}
