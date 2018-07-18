package com.sencerseven.blog.service;

import com.sencerseven.blog.domain.Category;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface CategoryService {

    List<Category> getCategoriesByActive(int status);

    Category getCategoriesByUrl(String url);
}
