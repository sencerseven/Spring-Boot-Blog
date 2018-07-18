package com.sencerseven.blog.service;

import com.sencerseven.blog.domain.Category;
import com.sencerseven.blog.exception.NotFoundCategoryException;
import com.sencerseven.blog.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService{

    CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getCategoriesByActive(int status) {
        return categoryRepository.findCategoriesByActive(status).stream().collect(Collectors.toList());

    }

    @Override
    public Category getCategoriesByUrl(String url) {
        Optional<Category> categoryOptional = categoryRepository.findCategoriesByUrl(url);

        if(!categoryOptional.isPresent())
            throw new NotFoundCategoryException();

        return categoryOptional.get();

    }
}
