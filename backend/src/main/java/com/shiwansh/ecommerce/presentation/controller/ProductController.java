package com.shiwansh.ecommerce.presentation.controller;

import com.shiwansh.ecommerce.application.dto.product.ProductResponse;
import com.shiwansh.ecommerce.application.usecase.product.GetProductUseCase;
import com.shiwansh.ecommerce.application.usecase.product.GetProductsUseCase;
import com.shiwansh.ecommerce.common.exception.ResourceNotFoundException;
import com.shiwansh.ecommerce.common.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final GetProductsUseCase getProductsUseCase;
    private final GetProductUseCase getProductUseCase;

    public ProductController(
            GetProductsUseCase getProductsUseCase,
            GetProductUseCase getProductUseCase) {

        this.getProductsUseCase = getProductsUseCase;
        this.getProductUseCase = getProductUseCase;
    }

    /*
     * CUSTOMER:
     * Get all ACTIVE products only
     */
    @GetMapping
    public ResponseEntity<ApiResponse<List<ProductResponse>>> getActiveProducts() {

        List<ProductResponse> activeProducts =
                getProductsUseCase.getAll()
                        .stream()
                        .filter(ProductResponse::isActive)
                        .toList();

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Active products fetched successfully",
                        activeProducts
                )
        );
    }

    /*
     * CUSTOMER:
     * Get one ACTIVE product only
     */
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponse>> getActiveProduct(
            @PathVariable Long id) {

        ProductResponse product =
                getProductUseCase.getById(id);

        if (!product.isActive()) {
            throw new ResourceNotFoundException(
                    "Product not found with id: " + id
            );
        }

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Product fetched successfully",
                        product
                )
        );
    }
}