package com.sencerseven.blog.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode(exclude = {"comment"})
@Entity
public class CommentUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String website;

    @OneToOne
    @JoinColumn(name = "comment_user_id")
    private Comment comment;

    public CommentUser() {
    }

    public CommentUser(String name, String email, String website) {
        this.name = name;
        this.email = email;
        this.website = website;
    }
}
