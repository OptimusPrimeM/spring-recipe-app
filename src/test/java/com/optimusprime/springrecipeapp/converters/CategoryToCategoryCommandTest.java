package com.optimusprime.springrecipeapp.converters;

import com.optimusprime.springrecipeapp.commands.CategoryCommand;
import com.optimusprime.springrecipeapp.domain.Category;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryToCategoryCommandTest {

    public static final String DESCRIPTION ="category";
    public static final Long ID_VALUE =  new Long(1L);

    CategoryToCategoryCommand converter;

    @Before
    public void setUp() throws Exception {
        converter = new CategoryToCategoryCommand();
    }

    @Test
    public void testNullParameter() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new Category()));
    }
    @Test
    public void convert() throws Exception {

        //given
        Category category = new Category();
        category.setId(ID_VALUE);
        category.setDesctrption(DESCRIPTION);

        //when
        CategoryCommand categoryCommand = converter.convert(category);

        //then
        assertEquals(ID_VALUE,categoryCommand.getId());
        assertEquals(DESCRIPTION,categoryCommand.getDesctrption());
    }

}