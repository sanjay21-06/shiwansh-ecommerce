package com.shiwansh.ecommerce.application.usecase.category;

import com.shiwansh.ecommerce.application.dto.category.CategoryCreateRequest;
import com.shiwansh.ecommerce.application.dto.category.CategoryResponse;

public interface CreateCategoryUseCase {

    CategoryResponse create(CategoryCreateRequest request);
}