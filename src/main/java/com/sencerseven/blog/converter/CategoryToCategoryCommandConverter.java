package com.sencerseven.blog.converter;

import com.sencerseven.blog.command.CategoryCommand;
import com.sencerseven.blog.domain.Category;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CategoryToCategoryCommandConverter implements Converter<Category, CategoryCommand> {


    @Override
    public CategoryCommand convert(Category category) {
        if (category == null)
            return null;

        CategoryCommand categoryCommand = new CategoryCommand();
        categoryCommand.setId(category.getId());
        categoryCommand.setName(category.getName());
        categoryCommand.setDescription(category.getDescription());
        categoryCommand.setActive(category.isActive());


        if (category.getParentCategory() != null && category.getParentCategory().getId() != null)
            categoryCommand.setParentCategory(this.convert(category.getParentCategory()));
        return categoryCommand;

    }
}
