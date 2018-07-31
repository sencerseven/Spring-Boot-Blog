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
@EqualsAndHashCode(exclude = {"category","comment","tags"})
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    @Column(columnDefinition="TEXT")
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

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
    @JoinTable(name = "POST_TAG"
        ,joinColumns = @JoinColumn(name = "post_id")
        ,inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tag> tags= new HashSet<>();

    @Column(columnDefinition = "int default 0")
    private int view;

    public Post addCategory(Category category){
        category.getPosts().add(this);
        this.category = category;
        return this;
    }

    public Post addTags(Tag tag){
        tag.getPosts().add(this);
        this.tags.add(tag);
        return this;
    }

    @PrePersist
    protected void onCreate() {
        if (createdAt == null) { createdAt = new Date(); }
    }

}
