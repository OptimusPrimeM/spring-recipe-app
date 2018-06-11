package com.optimusprime.springrecipeapp.converters;

import com.optimusprime.springrecipeapp.commands.UnitOfMeasureCommand;
import com.optimusprime.springrecipeapp.domain.UnitOfMeasure;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class UnitOfMeasureToUnitOfMeasureCommand implements Converter<UnitOfMeasure,UnitOfMeasureCommand> {

    @Synchronized
    @Nullable
    @Override
    public UnitOfMeasureCommand convert(UnitOfMeasure unitOfMeasure) {
        if(unitOfMeasure!=null){
            final  UnitOfMeasureCommand uomc= new UnitOfMeasureCommand();
            uomc.setId(unitOfMeasure.getId());
            uomc.setDescritpion(unitOfMeasure.getDescritpion());
            return  uomc;
        }
        return null;
    }
}
