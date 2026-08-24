package com.shiwansh.ecommerce.application.service.product;

import com.shiwansh.ecommerce.application.dto.product.ProductCreateRequest;
import com.shiwansh.ecommerce.application.dto.product.ProductResponse;
import com.shiwansh.ecommerce.application.dto.product.ProductUpdateRequest;
import com.shiwansh.ecommerce.application.usecase.product.CreateProductUseCase;
import com.shiwansh.ecommerce.application.usecase.product.DeleteProductUseCase;
import com.shiwansh.ecommerce.application.usecase.product.GetProductUseCase;
import com.shiwansh.ecommerce.application.usecase.product.GetProductsUseCase;
import com.shiwansh.ecommerce.application.usecase.product.UpdateProductUseCase;
import com.shiwansh.ecommerce.common.exception.ResourceNotFoundException;
import com.shiwansh.ecommerce.domain.model.Category;
import com.shiwansh.ecommerce.domain.model.Product;
import com.shiwansh.ecommerce.domain.repository.CategoryRepository;
import com.shiwansh.ecommerce.domain.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements
        CreateProductUseCase,
        GetProductsUseCase,
        GetProductUseCase,
        UpdateProductUseCase,
        DeleteProductUseCase {




    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(
            ProductRepository productRepository,
            CategoryRepository categoryRepository) {

        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public ProductResponse create(ProductCreateRequest request) {

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Category not found with id: "
                                        + request.getCategoryId()
                        ));

        Product product = new Product();

        product.setCategory(category);
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setSku(request.getSku());
        product.setActive(true);

        Product savedProduct = productRepository.save(product);

        return toResponse(savedProduct);
    }

    @Override
    public List<ProductResponse> getAll() {

        return productRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public ProductResponse getById(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Product not found with id: " + id
                        ));

        return toResponse(product);
    }

    @Override
    public ProductResponse update(
            Long id,
            ProductUpdateRequest request) {

        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Product not found with id: " + id
                        ));

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Category not found with id: "
                                        + request.getCategoryId()
                        ));

        product.setCategory(category);
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setSku(request.getSku());

        Product updatedProduct = productRepository.save(product);

        return toResponse(updatedProduct);
    }

    @Override
    public void delete(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Product not found with id: " + id
                        ));

        // Soft delete
        product.setActive(false);

        productRepository.save(product);
    }


    public List<ProductResponse> getActiveProducts() {

        return productRepository.findAll()
                .stream()
                .filter(Product::isActive)
                .map(this::toResponse)
                .toList();
    }


    public ProductResponse getActiveProductById(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Product not found with id: " + id
                        ));

        if (!product.isActive()) {
            throw new ResourceNotFoundException(
                    "Product is currently unavailable"
            );
        }

        return toResponse(product);
    }

    private ProductResponse toResponse(Product product) {

        return new ProductResponse(
                product.getId(),
                product.getCategory().getId(),
                product.getCategory().getName(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getSku(),
                product.isActive()
        );
    }
}