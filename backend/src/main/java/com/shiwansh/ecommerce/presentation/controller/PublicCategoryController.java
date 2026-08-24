package com.shiwansh.ecommerce.presentation.controller;

import com.shiwansh.ecommerce.application.dto.category.CategoryResponse;
import com.shiwansh.ecommerce.application.usecase.category.GetCategoriesUseCase;
import com.shiwansh.ecommerce.application.usecase.category.GetCategoryUseCase;
import com.shiwansh.ecommerce.common.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class PublicCategoryController {

    private final GetCategoriesUseCase getCategoriesUseCase;
    private final GetCategoryUseCase getCategoryUseCase;

    public PublicCategoryController(
            GetCategoriesUseCase getCategoriesUseCase,
            GetCategoryUseCase getCategoryUseCase) {

        this.getCategoriesUseCase = getCategoriesUseCase;
        this.getCategoryUseCase = getCategoryUseCase;
    }

    // ==========================================
    // GET ALL ACTIVE CATEGORIES
    // ==========================================

    @GetMapping
    public ResponseEntity<ApiResponse<List<CategoryResponse>>> getAll() {

        List<CategoryResponse> categories =
                getCategoriesUseCase.getAll();

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Categories fetched successfully",
                        categories
                )
        );
    }

    // ==========================================
    // GET CATEGORY BY ID
    // ==========================================

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryResponse>> getById(
            @PathVariable Long id) {

        CategoryResponse category =
                getCategoryUseCase.getById(id);

        return ResponseEntity.ok(
                ApiResponse.success(
                        "Category fetched successfully",
                        category
                )
        );
    }
}