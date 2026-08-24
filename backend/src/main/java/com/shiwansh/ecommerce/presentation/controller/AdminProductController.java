package com.shiwansh.ecommerce.presentation.controller;

import com.shiwansh.ecommerce.application.dto.product.ProductCreateRequest;
import com.shiwansh.ecommerce.application.dto.product.ProductResponse;
import com.shiwansh.ecommerce.application.dto.product.ProductUpdateRequest;
import com.shiwansh.ecommerce.application.usecase.product.CreateProductUseCase;
import com.shiwansh.ecommerce.application.usecase.product.DeleteProductUseCase;
import com.shiwansh.ecommerce.application.usecase.product.GetProductUseCase;
import com.shiwansh.ecommerce.application.usecase.product.GetProductsUseCase;
import com.shiwansh.ecommerce.application.usecase.product.UpdateProductUseCase;
import com.shiwansh.ecommerce.common.response.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/products")
public class AdminProductController {

    private final CreateProductUseCase createProductUseCase;
    private final GetProductsUseCase getProductsUseCase;
    private final GetProductUseCase getProductUseCase;
    private final UpdateProductUseCase updateProductUseCase;
    private final DeleteProductUseCase deleteProductUseCase;

    public AdminProductController(
            CreateProductUseCase createProductUseCase,
            GetProductsUseCase getProductsUseCase,
            GetProductUseCase getProductUseCase,
            UpdateProductUseCase updateProductUseCase,
            DeleteProductUseCase deleteProductUseCase) {

        this.createProductUseCase = createProductUseCase;
        this.getProductsUseCase = getProductsUseCase;
        this.getProductUseCase = getProductUseCase;
        this.updateProductUseCase = updateProductUseCase;
        this.deleteProductUseCase = deleteProductUseCase;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ProductResponse>> create(
            @Valid @RequestBody ProductCreateRequest request) {

        ProductResponse response =
                createProductUseCase.create(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success(
                        "Product created successfully",
                        response
                ));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProductResponse>>> getAll() {

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Products fetched successfully",
                        getProductsUseCase.getAll()
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponse>> getById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Product fetched successfully",
                        getProductUseCase.getById(id)
                )
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponse>> update(
            @PathVariable Long id,
            @Valid @RequestBody ProductUpdateRequest request) {

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Product updated successfully",
                        updateProductUseCase.update(id, request)
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id) {

        deleteProductUseCase.delete(id);

        return ResponseEntity.noContent().build();
    }
}