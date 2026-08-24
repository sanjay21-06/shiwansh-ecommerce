package com.shiwansh.ecommerce.domain.repository;

import com.shiwansh.ecommerce.domain.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {

    Category save(Category category);

    Optional<Category> findById(Long id);

    List<Category> findAll();

    void delete(Category category);

    void deleteById(Long id);
}