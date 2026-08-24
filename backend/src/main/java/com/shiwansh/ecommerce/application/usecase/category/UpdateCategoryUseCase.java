package com.shiwansh.ecommerce.application.usecase.category;

import com.shiwansh.ecommerce.application.dto.category.CategoryResponse;
import com.shiwansh.ecommerce.application.dto.category.CategoryUpdateRequest;

public interface UpdateCategoryUseCase {

    CategoryResponse update(Long id, CategoryUpdateRequest request);
}