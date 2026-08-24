package com.shiwansh.ecommerce.application.usecase.product;

import com.shiwansh.ecommerce.application.dto.product.ProductResponse;

public interface GetProductUseCase {

    ProductResponse getById(Long id);
}