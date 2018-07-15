package com.sencerseven.blog.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode(exclude = {"category"})
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private String text;

    private String url;

    @ManyToOne
    private Category category;

    @ManyToOne
    private Users users;

    @Temporal(TemporalType.DATE)
    private Date createdAt;

    public Post addCategory(Category category){
        category.getPosts().add(this);
        this.category = category;
        return this;
    }

}
