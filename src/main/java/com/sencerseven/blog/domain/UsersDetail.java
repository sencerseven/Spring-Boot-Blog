package com.sencerseven.blog.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
public class UsersDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private String profileImg;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    Users users;
}
