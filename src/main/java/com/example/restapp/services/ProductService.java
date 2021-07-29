package com.example.restapp.services;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import com.example.restapp.clients.CategoryClient;
import com.example.restapp.clients.models.Category;
import com.example.restapp.controllers.requests.CreateProductRequest;
import com.example.restapp.dto.ProductDto;
import com.example.restapp.models.Product;
import com.example.restapp.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    private final CategoryClient categoryClient;

    @Transactional
    public Product create(CreateProductRequest request) {
        Product product = Product.builder()
            .title(request.getTitle())
            .price(request.getPrice())
            .build();
        return this.productRepository.save(product);
    }

    @Cacheable("products")
    public ProductDto findById(Integer id) {
        Product product = this.productRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Product not found. Id: " + id));
        Category category = this.categoryClient.getCategory(3);
        return ProductDto.builder().title(product.getTitle()).price(product.getPrice()).category(category.getName()).build();
    }

}
