package com.optimusprime.springrecipeapp.converters;

import com.optimusprime.springrecipeapp.commands.CategoryCommand;
import com.optimusprime.springrecipeapp.domain.Category;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CategoryCommandToCategory implements Converter<CategoryCommand,Category> {

    @Synchronized
    @Nullable
    @Override
    public Category convert(CategoryCommand source) {
        if(source!=null){
            Category category= new Category();
            category.setId(source.getId());
            category.setDesctrption(source.getDesctrption());
            return category;
        }
        return null;
    }
}
