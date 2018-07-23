package com.sencerseven.blog.service;

import com.sencerseven.blog.command.CategoryCommand;
import com.sencerseven.blog.domain.Category;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface CategoryService {

    Category getById(Long id);

    List<Category> getCategoriesByActive(boolean status);

    Category getCategoriesByUrl(String url);

    Set<Category> getCategories();

    CategoryCommand saveCategoryCommand(CategoryCommand categoryCommand);

    void deleteCategory(Long id);

}
