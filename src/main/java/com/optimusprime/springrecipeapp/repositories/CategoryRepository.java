package com.optimusprime.springrecipeapp.repositories;

import com.optimusprime.springrecipeapp.domain.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository  extends CrudRepository<Category,Long>{
}
