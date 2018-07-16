package com.sencerseven.blog.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode(exclude = {"users","post"})
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    @Temporal(TemporalType.DATE)
    private Date createdAt;

    @ManyToOne
    private Users users;

    @ManyToOne
    private Post post;

    @Column(columnDefinition = "int default 0")
    private int active;

    @OneToOne(cascade = CascadeType.ALL)
    private CommentUser commentUser;

    public Comment addCommentUser(CommentUser commentUser){
        commentUser.setComment(this);
        this.commentUser = commentUser;
        return this;
    }


}
