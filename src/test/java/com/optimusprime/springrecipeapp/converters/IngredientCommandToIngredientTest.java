package com.optimusprime.springrecipeapp.converters;

import com.optimusprime.springrecipeapp.commands.IngredientCommand;
import com.optimusprime.springrecipeapp.commands.UnitOfMeasureCommand;
import com.optimusprime.springrecipeapp.domain.Ingredient;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class IngredientCommandToIngredientTest {

    public static final String DESCRIPTION ="CheeseBurger";
    public static final Long LONG_VALUE =  new Long(1L);
    public static final BigDecimal AMOUNT = new BigDecimal(1);
    public static final Long UOM_ID = new Long(2L);

    IngredientCommandToIngredient converter;

    @Before
    public void setUp() throws Exception {
        converter=  new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
    }

    @Test
    public void testNullParameter() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new IngredientCommand()));
    }
    @Test
    public void convert() throws Exception {

        //given
        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(LONG_VALUE);
        ingredientCommand.setAmount(AMOUNT);
        ingredientCommand.setDescription(DESCRIPTION);
        UnitOfMeasureCommand unitOfMeasureCommand=new UnitOfMeasureCommand();
        unitOfMeasureCommand.setId(UOM_ID);
        ingredientCommand.setUom(unitOfMeasureCommand);

        //when
        Ingredient ingredient=converter.convert(ingredientCommand);

        //then
        assertNotNull(ingredient);
        assertNotNull(ingredient.getUom());
        assertEquals(LONG_VALUE,ingredient.getId());
        assertEquals(AMOUNT,ingredient.getAmount());
        assertEquals(DESCRIPTION,ingredient.getDescription());
        assertEquals(UOM_ID,ingredient.getUom().getId());


    }

}