package com.sencerseven.blog.converter;

import com.sencerseven.blog.command.CategoryCommand;
import com.sencerseven.blog.domain.Category;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryToCategoryCommandConverterTest {

    CategoryToCategoryCommandConverter categoryToCategoryCommandConverter;
    private static final Long ID = 1L;


    @Before
    public void setUp() throws Exception {
        categoryToCategoryCommandConverter = new CategoryToCategoryCommandConverter();
    }

    @Test
    public void nullTest(){
        assertNull(categoryToCategoryCommandConverter.convert(null));
    }

    @Test
    public void emptyTest(){
        assertNotNull(categoryToCategoryCommandConverter.convert(new Category()));
    }

    @Test
    public void convert() {
        Category category = new Category();
        category.setId(ID);

        CategoryCommand categoryCommand = categoryToCategoryCommandConverter.convert(category);

        assertEquals(ID,categoryCommand.getId());


    }
}