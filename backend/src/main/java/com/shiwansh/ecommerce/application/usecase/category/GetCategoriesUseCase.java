package com.shiwansh.ecommerce.application.usecase.category;

import com.shiwansh.ecommerce.application.dto.category.CategoryResponse;

import java.util.List;

public interface GetCategoriesUseCase {

    List<CategoryResponse> getAll();
}