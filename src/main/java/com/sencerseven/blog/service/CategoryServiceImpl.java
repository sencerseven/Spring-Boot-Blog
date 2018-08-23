package com.sencerseven.blog.service;

import com.sencerseven.blog.command.CategoryCommand;
import com.sencerseven.blog.converter.CategoryCommandToCategoryConverter;
import com.sencerseven.blog.converter.CategoryToCategoryCommandConverter;
import com.sencerseven.blog.domain.Category;
import com.sencerseven.blog.exception.NotFoundCategoryException;
import com.sencerseven.blog.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service()
public class CategoryServiceImpl implements CategoryService {

    CategoryRepository categoryRepository;
    CategoryCommandToCategoryConverter categoryCommandToCategoryConverter;
    CategoryToCategoryCommandConverter categoryToCategoryCommandConverter;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryCommandToCategoryConverter categoryCommandToCategoryConverter, CategoryToCategoryCommandConverter categoryToCategoryCommandConverter) {
        this.categoryRepository = categoryRepository;
        this.categoryCommandToCategoryConverter = categoryCommandToCategoryConverter;
        this.categoryToCategoryCommandConverter = categoryToCategoryCommandConverter;
    }

    @Override
    public Category getById(Long id) {
       Optional<Category> optionalCategory =  categoryRepository.findById(id);

       if(!optionalCategory.isPresent())
           throw new RuntimeException();

       return optionalCategory.get();

    }

    @Override
    public List<Category> getCategoriesByActive(boolean status) {
        return categoryRepository.findCategoriesByActive(status).stream().collect(Collectors.toList());

    }

    @Override
    public Long countByActive(boolean status) {
        return categoryRepository.countByActive(status);
    }

    @Override
    public Category getCategoriesByUrl(String url) {
        Optional<Category> categoryOptional = categoryRepository.findCategoriesByUrl(url);

        if (!categoryOptional.isPresent())
            throw new NotFoundCategoryException();

        return categoryOptional.get();

    }

    @Override
    public Set<Category> getCategories() {
        return categoryRepository.findAll().stream().collect(Collectors.toSet());
    }

    @Override
    public CategoryCommand saveCategoryCommand(CategoryCommand categoryCommand) {
        if (categoryCommand != null) {
            Category category = categoryCommandToCategoryConverter.convert(categoryCommand);
            return categoryToCategoryCommandConverter.convert(categoryRepository.save(category));
        }

        return null;
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteCategoryById(id);
    }
}
