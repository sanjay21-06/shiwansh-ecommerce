package com.shiwansh.ecommerce.application.service.category;

import com.shiwansh.ecommerce.application.dto.category.CategoryCreateRequest;
import com.shiwansh.ecommerce.application.dto.category.CategoryResponse;
import com.shiwansh.ecommerce.application.dto.category.CategoryUpdateRequest;
import com.shiwansh.ecommerce.application.usecase.category.CreateCategoryUseCase;
import com.shiwansh.ecommerce.application.usecase.category.DeleteCategoryUseCase;
import com.shiwansh.ecommerce.application.usecase.category.GetCategoriesUseCase;
import com.shiwansh.ecommerce.application.usecase.category.GetCategoryUseCase;
import com.shiwansh.ecommerce.application.usecase.category.UpdateCategoryUseCase;
import com.shiwansh.ecommerce.domain.model.Category;
import com.shiwansh.ecommerce.domain.repository.CategoryRepository;
import org.springframework.stereotype.Service;import com.shiwansh.ecommerce.common.exception.ResourceNotFoundException;


import java.util.List;

@Service
public class CategoryService implements
        CreateCategoryUseCase,
        GetCategoriesUseCase,
        GetCategoryUseCase,
        UpdateCategoryUseCase,
        DeleteCategoryUseCase {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryResponse create(CategoryCreateRequest request) {

        Category category = new Category();

        category.setName(request.getName());
        category.setDescription(request.getDescription());
        category.setActive(request.isActive());

        Category savedCategory = categoryRepository.save(category);

        return toResponse(savedCategory);
    }

    @Override
    public List<CategoryResponse> getAll() {

        return categoryRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public CategoryResponse getById(Long id) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Category not found with id: " + id
                        ));

        return toResponse(category);
    }

    @Override
    public CategoryResponse update(
            Long id,
            CategoryUpdateRequest request) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Category not found with id: " + id
                        ));

        category.setName(request.getName());
        category.setDescription(request.getDescription());
        category.setActive(request.isActive());

        Category updatedCategory = categoryRepository.save(category);

        return toResponse(updatedCategory);
    }

    @Override
    public void delete(Long id) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Category not found with id: " + id
                        )
                );

        category.setActive(false);

        categoryRepository.save(category);
    }

    private CategoryResponse toResponse(Category category) {

        return new CategoryResponse(
                category.getId(),
                category.getName(),
                category.getDescription(),
                category.isActive()
        );
    }
}