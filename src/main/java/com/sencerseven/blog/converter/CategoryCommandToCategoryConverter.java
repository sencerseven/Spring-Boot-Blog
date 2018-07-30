package com.sencerseven.blog.converter;

import com.sencerseven.blog.command.CategoryCommand;
import com.sencerseven.blog.domain.Category;
import com.sencerseven.blog.functions.BlogHelpers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CategoryCommandToCategoryConverter implements Converter<CategoryCommand,Category> {

    @Autowired
    BlogHelpers blogHelpers;


    @Override
    public Category convert(CategoryCommand categoryCommand) {
       if(categoryCommand == null)
           return null;

       Category category = new Category();
       category.setId(categoryCommand.getId());
       category.setName(categoryCommand.getName());
       category.setDescription(categoryCommand.getDescription());
       category.setActive(categoryCommand.isActive());
       if (categoryCommand.getName() != null)
       category.setUrl(blogHelpers.toSlug(categoryCommand.getName()));


       if(categoryCommand.getParentCategory() != null && categoryCommand.getParentCategory().getId() != null)
       category.setParentCategory(this.convert(categoryCommand.getParentCategory()));

       return category;


    }
}
