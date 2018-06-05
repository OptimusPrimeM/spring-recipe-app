package com.optimusprime.springrecipeapp.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryTest {

    Category category;

    @Before
    public void setUp() throws Exception {
       category = new Category();
    }

    @Test
    public void getId() throws Exception {

        Long idValue =  4l;
        category.setId(idValue);
        assertEquals(idValue,category.getId());
    }

    @Test
    public void getDesctrption() throws Exception {
    }
    @Test
    public void getRecipes() throws Exception {
    }

}