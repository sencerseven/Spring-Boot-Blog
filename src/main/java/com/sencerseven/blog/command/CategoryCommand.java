package com.sencerseven.blog.command;


import com.sencerseven.blog.domain.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryCommand {

    private Long id;

    private String name;

    private String description;

    private CategoryCommand parentCategory;

    private boolean active;

    private String url;

    public CategoryCommand(String name, String description, CategoryCommand parentCategory, boolean active, String url) {
        this.name = name;
        this.description = description;
        this.parentCategory = parentCategory;
        this.active = active;
        this.url = url;
    }
}
