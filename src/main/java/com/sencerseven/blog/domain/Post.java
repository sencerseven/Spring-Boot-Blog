package com.sencerseven.blog.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(exclude = {"category","comment"})
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private String text;

    private String url;

    private String imageUrl;

    @ManyToOne
    private Category category;

    @ManyToOne
    private Users users;

    @Temporal(TemporalType.DATE)
    private Date createdAt;

    @OneToMany(mappedBy = "post",fetch = FetchType.LAZY)
    private Set<Comment> comment = new HashSet<>();

    private Long view;

    public Post addCategory(Category category){
        category.getPosts().add(this);
        this.category = category;
        return this;
    }

}
