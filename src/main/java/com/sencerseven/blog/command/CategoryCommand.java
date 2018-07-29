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


}
