package com.optimusprime.springrecipeapp.bootstrap;

import com.optimusprime.springrecipeapp.domain.Category;
import com.optimusprime.springrecipeapp.domain.UnitOfMeasure;
import com.optimusprime.springrecipeapp.repositories.CategoryRepository;
import com.optimusprime.springrecipeapp.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Profile({"dev","prod"})
public class BootStrapMySQL implements ApplicationListener<ContextRefreshedEvent> {

    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public BootStrapMySQL(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (categoryRepository.count() == 0L){
            log.debug("Loading categories");
            loadCategories();
        }
        if (unitOfMeasureRepository.count() == 0L){
            log.debug("Loading UOMs");
            loadUom();
        }

    }

    private void loadUom() {
        UnitOfMeasure uom1 = new UnitOfMeasure();
        uom1.setDescritpion("Teaspoon");
        unitOfMeasureRepository.save(uom1);

        UnitOfMeasure uom2 = new UnitOfMeasure();
        uom2.setDescritpion("Tablespoon");
        unitOfMeasureRepository.save(uom2);

        UnitOfMeasure uom3 = new UnitOfMeasure();
        uom3.setDescritpion("Cup");
        unitOfMeasureRepository.save(uom3);

        UnitOfMeasure uom4 = new UnitOfMeasure();
        uom4.setDescritpion("Pinch");
        unitOfMeasureRepository.save(uom4);

        UnitOfMeasure uom5 = new UnitOfMeasure();
        uom5.setDescritpion("Ounce");
        unitOfMeasureRepository.save(uom5);

        UnitOfMeasure uom6 = new UnitOfMeasure();
        uom6.setDescritpion("Each");
        unitOfMeasureRepository.save(uom6);

        UnitOfMeasure uom7 = new UnitOfMeasure();
        uom7.setDescritpion("Pint");
        unitOfMeasureRepository.save(uom7);

        UnitOfMeasure uom8 = new UnitOfMeasure();
        uom8.setDescritpion("Dash");
        unitOfMeasureRepository.save(uom8);
    }

    private void loadCategories() {
        Category category1 = new Category();
        category1.setDesctrption("American");
        categoryRepository.save(category1);

        Category category2 = new Category();
        category2.setDesctrption("Italian");
        categoryRepository.save(category2);

        Category category3 = new Category();
        category3.setDesctrption("Mexican");
        categoryRepository.save(category3);

        Category category4 = new Category();
        category4.setDesctrption("Fast Food");
        categoryRepository.save(category4);
    }
}
