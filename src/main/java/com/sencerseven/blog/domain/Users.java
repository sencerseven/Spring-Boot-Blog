package com.sencerseven.blog.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(exclude = {"postSet","usersDetail"})
@Entity
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    private String userName;

    private String lastName;

    private int active;

    @OneToOne(mappedBy = "users",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private UsersDetail usersDetail;

    @OneToMany(mappedBy = "users",fetch = FetchType.LAZY)
    private Set<Comment> comment = new HashSet<>();

    @OneToMany(mappedBy = "users",fetch = FetchType.LAZY)
    private Set<Post> postSet = new HashSet<>();

}
