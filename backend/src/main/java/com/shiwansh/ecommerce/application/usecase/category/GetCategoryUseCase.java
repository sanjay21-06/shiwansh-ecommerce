package com.shiwansh.ecommerce.application.usecase.category;

import com.shiwansh.ecommerce.application.dto.category.CategoryResponse;

public interface GetCategoryUseCase {

    CategoryResponse getById(Long id);
}