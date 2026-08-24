package com.shiwansh.ecommerce.application.usecase.product;

import com.shiwansh.ecommerce.application.dto.product.ProductCreateRequest;
import com.shiwansh.ecommerce.application.dto.product.ProductResponse;

public interface CreateProductUseCase {

    ProductResponse create(ProductCreateRequest request);
}