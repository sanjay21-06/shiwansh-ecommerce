package com.shiwansh.ecommerce.infrastructure.persistence.repository.adapter;

import com.shiwansh.ecommerce.domain.model.Category;
import com.shiwansh.ecommerce.domain.repository.CategoryRepository;
import com.shiwansh.ecommerce.infrastructure.persistence.entity.CategoryEntity;
import com.shiwansh.ecommerce.infrastructure.persistence.repository.CategoryJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CategoryRepositoryAdapter implements CategoryRepository {

    private final CategoryJpaRepository categoryJpaRepository;

    public CategoryRepositoryAdapter(CategoryJpaRepository categoryJpaRepository) {
        this.categoryJpaRepository = categoryJpaRepository;
    }

    @Override
    public Category save(Category category) {
        CategoryEntity entity = toEntity(category);

        CategoryEntity savedEntity = categoryJpaRepository.save(entity);

        return toDomain(savedEntity);
    }

    @Override
    public Optional<Category> findById(Long id) {
        return categoryJpaRepository.findById(id)
                .map(this::toDomain);
    }

    @Override
    public List<Category> findAll() {
        return categoryJpaRepository.findAll()
                .stream()
                .map(this::toDomain)
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        categoryJpaRepository.deleteById(id);
    }

    private CategoryEntity toEntity(Category category) {

        CategoryEntity entity = new CategoryEntity();

        entity.setId(category.getId());
        entity.setName(category.getName());
        entity.setDescription(category.getDescription());
        entity.setActive(category.isActive());

        return entity;
    }

    private Category toDomain(CategoryEntity entity) {

        return new Category(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.isActive()
        );
    }
}