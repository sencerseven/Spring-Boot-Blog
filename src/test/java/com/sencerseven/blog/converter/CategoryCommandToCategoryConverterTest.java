package com.sencerseven.blog.converter;

import com.sencerseven.blog.command.CategoryCommand;
import com.sencerseven.blog.domain.Category;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;

public class CategoryCommandToCategoryConverterTest {

    CategoryCommandToCategoryConverter categoryCommandToCategoryConverter;

    private static final Long ID =1L;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        categoryCommandToCategoryConverter = new CategoryCommandToCategoryConverter();

    }

    @Test
    public void nullTest(){
        assertNull(categoryCommandToCategoryConverter.convert(null));
    }

    @Test
    public void emptyTest(){
        assertNotNull(categoryCommandToCategoryConverter.convert(new CategoryCommand()));
    }


    @Test
    public void convert() {
        CategoryCommand categoryCommand = new CategoryCommand();
        categoryCommand.setId(ID);

        Category category = categoryCommandToCategoryConverter.convert(categoryCommand);

        assertEquals(ID,category.getId());

    }
}