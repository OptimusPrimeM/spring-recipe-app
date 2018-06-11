package com.optimusprime.springrecipeapp.converters;


import com.optimusprime.springrecipeapp.commands.IngredientCommand;
import com.optimusprime.springrecipeapp.domain.Ingredient;
import com.optimusprime.springrecipeapp.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;

public class IngredientToIngredientCommandTest {

    public static final String DESCRIPTION ="CheeseBurger";
    public static final Long LONG_VALUE =  new Long(1L);
    public static final BigDecimal AMOUNT = new BigDecimal(1);
    public static final Long UOM_ID = new Long(2L);

    IngredientToIngredientCommand converter;

    @Before
    public void setUp() throws Exception {
        converter =  new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
    }

    @Test
    public void testNullParameter() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new Ingredient()));
    }
    @Test
    public void convert() throws Exception {

        //given
        Ingredient ingredient= new Ingredient();
        ingredient.setId(LONG_VALUE);
        ingredient.setAmount(AMOUNT);
        ingredient.setDescription(DESCRIPTION);
        UnitOfMeasure uom= new UnitOfMeasure();
        uom.setId(UOM_ID);
        ingredient.setUom(uom);

        //when
        IngredientCommand ingredientCommand=converter.convert(ingredient);

        //then
        assertNotNull(ingredientCommand);
        assertNotNull(ingredientCommand.getUnitOfMeasure());
        assertEquals(LONG_VALUE,ingredientCommand.getId());
        assertEquals(AMOUNT,ingredientCommand.getAmount());
        assertEquals(DESCRIPTION,ingredientCommand.getDescription());
        assertEquals(UOM_ID,ingredientCommand.getUnitOfMeasure().getId());
    }

}