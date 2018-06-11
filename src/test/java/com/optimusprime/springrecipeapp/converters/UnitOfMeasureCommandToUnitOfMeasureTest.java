package com.optimusprime.springrecipeapp.converters;

import com.optimusprime.springrecipeapp.commands.UnitOfMeasureCommand;
import com.optimusprime.springrecipeapp.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UnitOfMeasureCommandToUnitOfMeasureTest {

    public static final String DESCRIPTION ="description";
    public static final Long LONG_VALUE =  new Long(1L);

    UnitOfMeasureCommandToUnitOfMeasure conveter;

    @Before
    public void setUp() throws Exception {
        conveter = new UnitOfMeasureCommandToUnitOfMeasure();
    }

    @Test
    public void testNullParameter() throws Exception {
       assertNull(conveter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(conveter.convert(new UnitOfMeasureCommand()));
    }

    @Test
    public void convert() throws Exception {

        //given
        UnitOfMeasureCommand command = new UnitOfMeasureCommand();
        command.setId(LONG_VALUE);
        command.setDescritpion(DESCRIPTION);

        //when
        UnitOfMeasure uom= conveter.convert(command);

        //then
        assertNotNull(uom);
        assertEquals(LONG_VALUE,uom.getId());
        assertEquals(DESCRIPTION,uom.getDescritpion());
    }

}