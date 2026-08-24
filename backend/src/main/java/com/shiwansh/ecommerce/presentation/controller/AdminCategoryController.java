package com.shiwansh.ecommerce.presentation.controller;

import com.shiwansh.ecommerce.application.dto.category.CategoryCreateRequest;
import com.shiwansh.ecommerce.application.dto.category.CategoryResponse;
import com.shiwansh.ecommerce.application.dto.category.CategoryUpdateRequest;

import com.shiwansh.ecommerce.application.usecase.category.CreateCategoryUseCase;
import com.shiwansh.ecommerce.application.usecase.category.DeleteCategoryUseCase;
import com.shiwansh.ecommerce.application.usecase.category.GetCategoriesUseCase;
import com.shiwansh.ecommerce.application.usecase.category.GetCategoryUseCase;
import com.shiwansh.ecommerce.application.usecase.category.UpdateCategoryUseCase;

import com.shiwansh.ecommerce.common.response.ApiResponse;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/categories")
public class AdminCategoryController {

    private final CreateCategoryUseCase createCategoryUseCase;
    private final GetCategoriesUseCase getCategoriesUseCase;
    private final GetCategoryUseCase getCategoryUseCase;
    private final UpdateCategoryUseCase updateCategoryUseCase;
    private final DeleteCategoryUseCase deleteCategoryUseCase;

    public AdminCategoryController(
            CreateCategoryUseCase createCategoryUseCase,
            GetCategoriesUseCase getCategoriesUseCase,
            GetCategoryUseCase getCategoryUseCase,
            UpdateCategoryUseCase updateCategoryUseCase,
            DeleteCategoryUseCase deleteCategoryUseCase) {

        this.createCategoryUseCase = createCategoryUseCase;
        this.getCategoriesUseCase = getCategoriesUseCase;
        this.getCategoryUseCase = getCategoryUseCase;
        this.updateCategoryUseCase = updateCategoryUseCase;
        this.deleteCategoryUseCase = deleteCategoryUseCase;
    }

    // ==========================================
    // CREATE CATEGORY
    // POST /api/admin/categories
    // ==========================================

    @PostMapping
    public ResponseEntity<ApiResponse<CategoryResponse>> create(
            @Valid @RequestBody CategoryCreateRequest request) {

        CategoryResponse response =
                createCategoryUseCase.create(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        ApiResponse.success(
                                "Category created successfully",
                                response
                        )
                );
    }

    // ==========================================
    // GET ALL CATEGORIES
    // GET /api/admin/categories
    // ==========================================

    @GetMapping
    public ResponseEntity<ApiResponse<List<CategoryResponse>>> getAll() {

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Categories fetched successfully",
                        getCategoriesUseCase.getAll()
                )
        );
    }

    // ==========================================
    // GET CATEGORY BY ID
    // GET /api/admin/categories/{id}
    // ==========================================

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryResponse>> getById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Category fetched successfully",
                        getCategoryUseCase.getById(id)
                )
        );
    }

    // ==========================================
    // UPDATE CATEGORY
    // PUT /api/admin/categories/{id}
    // ==========================================

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryResponse>> update(
            @PathVariable Long id,
            @Valid @RequestBody CategoryUpdateRequest request) {

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Category updated successfully",
                        updateCategoryUseCase.update(id, request)
                )
        );
    }

    // ==========================================
    // DELETE CATEGORY
    // DELETE /api/admin/categories/{id}
    // ==========================================

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id) {

        deleteCategoryUseCase.delete(id);

        return ResponseEntity.noContent().build();
    }
}