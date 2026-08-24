package com.shiwansh.ecommerce.application.usecase.product;

import com.shiwansh.ecommerce.application.dto.product.ProductResponse;

import java.util.List;

public interface GetProductsUseCase {

    List<ProductResponse> getAll();
}