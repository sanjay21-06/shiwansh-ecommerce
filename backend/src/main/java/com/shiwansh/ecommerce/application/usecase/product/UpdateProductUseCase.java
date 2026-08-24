package com.shiwansh.ecommerce.application.usecase.product;

import com.shiwansh.ecommerce.application.dto.product.ProductResponse;
import com.shiwansh.ecommerce.application.dto.product.ProductUpdateRequest;

public interface UpdateProductUseCase {

    ProductResponse update(Long id, ProductUpdateRequest request);
}