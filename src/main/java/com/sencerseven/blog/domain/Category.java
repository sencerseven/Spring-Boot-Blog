package com.sencerseven.blog.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private String url;

    private boolean active;

    @ManyToOne
    @JoinColumn(name = "PARENT_CATEGORY_ID")
    private Category parentCategory;

    @OneToMany(mappedBy = "parentCategory", cascade = {CascadeType.DETACH, CascadeType.REFRESH, CascadeType.MERGE, CascadeType.REMOVE},
            orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Category> childrenCategory = new HashSet<>();

    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<Post> posts = new HashSet<>();

    public Category addChildren(Category category) {
        this.getChildrenCategory().add(category);
        category.setParentCategory(this);
        return this;
    }

    public Category removeChildren(final Category childCategory) {
        if (childCategory != null)
            childCategory.setParentCategory(null);
        return this;
    }

}
