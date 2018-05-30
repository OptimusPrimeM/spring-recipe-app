package com.optimusprime.springrecipeapp.repositories;

import com.optimusprime.springrecipeapp.domain.Category;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CategoryRepository  extends CrudRepository<Category,Long>{

    Optional<Category> findByDesctrption(String description);
}
