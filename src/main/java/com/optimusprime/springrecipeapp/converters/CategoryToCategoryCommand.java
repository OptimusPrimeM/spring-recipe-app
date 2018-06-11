package com.optimusprime.springrecipeapp.converters;

import com.optimusprime.springrecipeapp.commands.CategoryCommand;
import com.optimusprime.springrecipeapp.domain.Category;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CategoryToCategoryCommand implements Converter<Category, CategoryCommand> {

    @Synchronized
    @Nullable
    @Override
    public CategoryCommand convert(Category source) {
        if (source != null) {
            CategoryCommand categoryCommand = new CategoryCommand();
            categoryCommand.setId(source.getId());
            categoryCommand.setDesctrption(source.getDesctrption());
            return categoryCommand;
        }
        return null;
    }
}
