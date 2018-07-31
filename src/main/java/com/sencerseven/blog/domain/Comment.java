package com.sencerseven.blog.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode(exclude = {"users", "post","usersDetail"})
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    @Temporal(TemporalType.DATE)
    private Date createdAt;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.DETACH,CascadeType.REFRESH,CascadeType.MERGE})
    private UsersDetail usersDetail;

    @ManyToOne
    private Post post;

    @Column(columnDefinition = "int default 0")
    private int active;

    private String type;

    public Comment addUsersDetail(UsersDetail usersDetail) {
        usersDetail.getComment().add(this);
        this.usersDetail = usersDetail;
        return this;
    }


}
